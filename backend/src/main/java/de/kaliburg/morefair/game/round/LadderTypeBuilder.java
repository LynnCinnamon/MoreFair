package de.kaliburg.morefair.game.round;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LadderTypeBuilder {

  private static final Random random = new Random();
  private final Map<LadderType, Float> ladderSizeTypeWeights = new HashMap<>();
  private final Map<LadderType, Float> ladderAutoTypeWeights = new HashMap<>();
  @Setter
  @Accessors(chain = true)
  private Set<RoundType> roundTypes = EnumSet.noneOf(RoundType.class);
  @Setter
  @Accessors(chain = true)
  private Set<LadderType> previousLadderType = EnumSet.noneOf(LadderType.class);
  @Setter
  @Accessors(chain = true)
  private Integer ladderNumber;
  @Setter
  @Accessors(chain = true)
  private Integer roundNumber = 1;
  @Setter
  @Accessors(chain = true)
  private Integer assholeLadderNumber;

  public LadderTypeBuilder() {
    ladderSizeTypeWeights.put(LadderType.TINY, 1.f);
    ladderSizeTypeWeights.put(LadderType.SMALL, 20.f);
    ladderSizeTypeWeights.put(LadderType.BIG, 20.f);
    ladderSizeTypeWeights.put(LadderType.GIGANTIC, 1.f);
    ladderSizeTypeWeights.put(LadderType.DEFAULT, 50.f);

    ladderAutoTypeWeights.put(LadderType.FREE_AUTO, 5.f);
    ladderAutoTypeWeights.put(LadderType.NO_AUTO, 2.f);
    ladderAutoTypeWeights.put(LadderType.DEFAULT, 100.f);
  }

  private void handlePreviousLadderType(LadderType ladderType) {
    // CHAOS disables back to back protection but promotes a different ladder Type each time
    if (roundTypes.contains(RoundType.CHAOS)) {
      if (ladderSizeTypeWeights.containsKey(ladderType)) {
        ladderSizeTypeWeights.put(ladderType, ladderSizeTypeWeights.get(ladderType) / 2);
      }
      if (ladderAutoTypeWeights.containsKey(ladderType)) {
        ladderAutoTypeWeights.put(ladderType, ladderAutoTypeWeights.get(ladderType) / 2);
      }

      return;
    }

    if (roundTypes.contains(RoundType.SLOW)) {
      return;
    }

    switch (ladderType) {
      case BIG, GIGANTIC -> {
        ladderSizeTypeWeights.put(LadderType.BIG, 0.f);
      }
      case NO_AUTO -> {
        ladderAutoTypeWeights.put(LadderType.NO_AUTO,
            ladderAutoTypeWeights.get(LadderType.NO_AUTO) / 2);
      }
      default -> {
        // do nothing
      }
    }
  }

  private void handleRoundTypes(RoundType roundType) {
    switch (roundType) {
      case FAST -> {
        ladderSizeTypeWeights.put(LadderType.TINY, ladderSizeTypeWeights.get(LadderType.TINY) * 2);
        ladderSizeTypeWeights.put(LadderType.BIG, 0.f);
        ladderSizeTypeWeights.put(LadderType.GIGANTIC, 0.f);
        ladderSizeTypeWeights.put(LadderType.DEFAULT, 0.f);
      }
      case AUTO -> {
        ladderAutoTypeWeights.put(LadderType.FREE_AUTO,
            Math.max(1.0f, ladderAutoTypeWeights.get(LadderType.FREE_AUTO) * 10));
        ladderAutoTypeWeights.put(LadderType.DEFAULT, 0.f);
      }
      case CHAOS -> {
        ladderSizeTypeWeights.put(LadderType.TINY, 1.f);
        ladderSizeTypeWeights.put(LadderType.SMALL, 1.f);
        ladderSizeTypeWeights.put(LadderType.BIG, 1.f);
        ladderSizeTypeWeights.put(LadderType.GIGANTIC, 1.f);
        ladderSizeTypeWeights.put(LadderType.DEFAULT, 1.f);
      }
      case SLOW -> {
        ladderSizeTypeWeights.put(LadderType.TINY, 0.f);
        ladderSizeTypeWeights.put(LadderType.SMALL, 0.f);
        ladderSizeTypeWeights.put(LadderType.DEFAULT,
            ladderSizeTypeWeights.get(LadderType.DEFAULT) / 5);
        ladderSizeTypeWeights.put(LadderType.GIGANTIC,
            ladderSizeTypeWeights.get(LadderType.GIGANTIC) * 2);
        ladderAutoTypeWeights.put(LadderType.NO_AUTO, 0.f);
        ladderAutoTypeWeights.put(LadderType.FREE_AUTO,
            ladderAutoTypeWeights.get(LadderType.FREE_AUTO) * 2);
      }
      default -> {
        // do nothing
      }
    }
  }

  public Set<LadderType> build() {
    Set<LadderType> ladderTypes = EnumSet.noneOf(LadderType.class);

    if (ladderNumber == 1) {
      ladderSizeTypeWeights.put(LadderType.TINY, 0.f);
      ladderSizeTypeWeights.put(LadderType.GIGANTIC, 0.f);
      ladderSizeTypeWeights.put(LadderType.SMALL, 0.f);
      ladderSizeTypeWeights.put(LadderType.BIG, 0.f);
      ladderAutoTypeWeights.put(LadderType.FREE_AUTO, 0.f);
      ladderAutoTypeWeights.put(LadderType.NO_AUTO, 0.f);
    }

    if (ladderNumber > 25) {
      ladderSizeTypeWeights.put(LadderType.GIGANTIC, 0.f);
    }

    this.roundTypes.stream().sorted(new RoundTypeComparator()).forEach(this::handleRoundTypes);
    this.previousLadderType.stream().sorted(new LadderTypeComparator())
        .forEach(this::handlePreviousLadderType);

    if (roundNumber == 100 && ladderNumber % 10 == 0) {
      // make it no Auto for sure
      ladderAutoTypeWeights.put(LadderType.DEFAULT, 0.f);
      ladderAutoTypeWeights.put(LadderType.FREE_AUTO, 0.f);
      ladderAutoTypeWeights.put(LadderType.NO_AUTO, 100.f);
    }

    if (ladderNumber >= assholeLadderNumber) {
      ladderAutoTypeWeights.put(LadderType.NO_AUTO,
          Math.max(1.f, ladderAutoTypeWeights.get(LadderType.NO_AUTO)));
      ladderAutoTypeWeights.put(LadderType.FREE_AUTO, 0.f);
      ladderAutoTypeWeights.put(LadderType.DEFAULT, 0.f);
      ladderTypes.add(LadderType.ASSHOLE);
    }

    ladderTypes.add(getRandomLadderType(ladderSizeTypeWeights, "Size"));
    ladderTypes.add(getRandomLadderType(ladderAutoTypeWeights, "Auto"));
    if (ladderTypes.size() > 1) {
      ladderTypes.remove(LadderType.DEFAULT);
    }
    return ladderTypes;
  }

  private Map<Float, LadderType> createInverseLookupTable(Map<LadderType, Float> weights) {
    Map<Float, LadderType> inverseLookupTable = new HashMap<>();
    float currentWeight = 0;
    for (Map.Entry<LadderType, Float> entry : weights.entrySet()) {
      if (entry.getValue() <= 0) {
        continue;
      }
      currentWeight += entry.getValue();
      inverseLookupTable.put(currentWeight, entry.getKey());
    }
    return inverseLookupTable;
  }

  private LadderType getRandomLadderType(Map<LadderType, Float> weights, String categoryName) {
    float totalWeight = weights.values().stream().reduce(0.f, Float::sum);
    float randomNumber = random.nextFloat(totalWeight);
    List<Entry<Float, LadderType>> inverseLookupEntries = createInverseLookupTable(
        weights).entrySet().stream().sorted(Entry.comparingByKey()).toList();

    log.info("Random {} percentage for L{}: {}/{}", categoryName, ladderNumber, randomNumber,
        totalWeight);
    for (Map.Entry<Float, LadderType> entry : inverseLookupEntries) {
      log.info("Checking {} percentage: {}/{}", entry.getValue(), entry.getKey(), totalWeight);
      if (randomNumber < entry.getKey()) {
        return entry.getValue();
      }
    }
    return LadderType.DEFAULT;
  }
}
