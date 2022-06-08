import { Sounds } from "@/modules/sounds";
import store from "@/store";
import Decimal from "break_infinity.js";
import { computed, markRaw } from "vue";

const gotFirstJingleVolume = computed(() =>
  store.getters["options/getOptionValue"]("notificationVolume")
);

class LadderStats {
  constructor() {
    this.growingRankerCount = 1;
    this.pointsNeededForManualPromote = new Decimal(Infinity);
    this.nonReactive = markRaw({
      playerWasFirstLastTick: true,
    });

    //Since we get instanciated in a store sub module, we need to do a setTimeout to make sure the store is ready.
    //This ensures that the store is fully created and in the next tick we can access the store normally.
    setTimeout(() => {
      Sounds.register("gotFirstJingle", require("@/assets/gotFirstJingle.wav"));
    }, 0);
  }

  async calculateStats(ladder, rankers) {
    //calculate vinegar decay
    let vinDecay = 0.9975;

    for (var i = 1; i < rankers.length; i++) {
      let ranker = rankers[i];
      if (ranker.timeSinceTop > 60 * 5) {
        //only reset the vin decay after 5 minutes off the top
        ranker.estimatedVinegarLeft = 1;
      }
      ranker.timeSinceTop += 1;
    }

    if (rankers[0].growing && !this.pointsNeededForManualPromote.eq(Infinity)) {
      rankers[0].estimatedVinegarLeft =
        rankers[0].estimatedVinegarLeft * vinDecay;
      rankers[0].timeSinceTop = 0;
    }

    //Now we calc the logic for the "hey you just came first" jingle
    if (
      !this.nonReactive.playerWasFirstLastTick &&
      ladder.yourRanker.you &&
      ladder.yourRanker.rank === 1
    ) {
      this.nonReactive.playerWasFirstLastTick = true;
      Sounds.play("gotFirstJingle", gotFirstJingleVolume.value);
    }
    if (ladder.yourRanker.you && ladder.yourRanker.rank !== 1) {
      this.nonReactive.playerWasFirstLastTick = false;
    }

    return rankers;
  }

  calculatePointsNeededForPromote(ladder, settings) {
    this.growingRankerCount = ladder.rankers.filter(
      (ranker) => ranker.growing
    ).length;

    // If not enough Players -> Infinity
    /*
    if (
      ladder.rankers.length <
      Math.max(settings.minimumPeopleForPromote, ladder.ladderNumber)
    ) {
      this.pointsNeededForManualPromote = new Decimal(Infinity);
      return;
    }*/

    // If not enough points -> minimum required Points
    if (
      ladder.firstRanker.points.cmp(
        settings.pointsForPromote.mul(ladder.ladderNumber)
      ) < 0
    ) {
      this.pointsNeededForManualPromote = settings.pointsForPromote.mul(
        ladder.ladderNumber
      );
      return;
    }

    // If before autopromote unlocks -> 1st place
    if (ladder.ladderNumber < settings.autoPromoteLadder) {
      this.pointsNeededForManualPromote = ladder.firstRanker.you
        ? ladder.rankers[1].points.add(1)
        : ladder.firstRanker.points.add(1);
      return;
    }

    let leadingRanker = ladder.firstRanker.you
      ? ladder.yourRanker
      : ladder.firstRanker;
    let pursuingRanker = ladder.firstRanker.you
      ? ladder.rankers[1]
      : ladder.yourRanker;

    // How many more points does the ranker gain against his pursuer, every Second
    let powerDiff = (
      leadingRanker.growing ? leadingRanker.power : new Decimal(0)
    ).sub(pursuingRanker.growing ? pursuingRanker.power : 0);
    // Calculate the needed Point difference, to have f.e. 30seconds of point generation with the difference in power
    let neededPointDiff = powerDiff.mul(settings.manualPromoteWaitTime).abs();

    this.pointsNeededForManualPromote = Decimal.max(
      (leadingRanker.you ? pursuingRanker : leadingRanker).points.add(
        neededPointDiff
      ),
      settings.pointsForPromote.mul(ladder.ladderNumber)
    );
  }
}

export default LadderStats;
