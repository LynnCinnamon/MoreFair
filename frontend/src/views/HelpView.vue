<template>
  <div class="scroller">
    <div class="container">
      <div id="accordionExample" class="accordion">
        <div class="accordion-item">
          <h2 id="headingDiscord" class="accordion-header">
            <button
              aria-controls="collapseDiscord"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapseDiscord"
              data-bs-toggle="collapse"
              type="button"
            >
              Rules & Discord
            </button>
          </h2>
          <div
            id="collapseDiscord"
            aria-labelledby="headingDiscord"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              Please follow the rules below to keep things fair and comfy:
              <ol>
                <li>
                  The game is in testing phase; we ask that you report any bugs
                  found and are not abusing them.
                </li>
                <li>
                  A basic measure of respect, regarding the users of this
                  server, is necessary.
                </li>
                <li>
                  We don't tolerate any form of racism, homophobia, transphobia
                  and/or any hostile statements toward LGBTQ+.
                </li>
                <li>
                  This server is for all age-groups, so all pornographic and/or
                  NSFL content is forbidden.
                </li>
                <li>
                  Please keep the chat in english and the usernames
                  interpretable.
                </li>
                <li>
                  We don't allow promotion and/or advertisement unless the
                  explicitly permitted.
                </li>
                <li>Don't use any malicious and/or automating scripts.</li>
                <li>Don't use multiple accounts during the same round.</li>
                <li>No posting of uuids and/or sensitive data.</li>
              </ol>

              If you have any questions, ideas, want to stay up to date to the
              latest updates or simply just want to chat with your fellow
              rankers, without having Chad listening, you can join us on our
              <a href="https://discord.gg/ThKzCknfFr" target="_blank">Discord</a
              >.<br /><br />
              I appreciate everyone that's part of this amazing community.
              <strong>Thank you!</strong> <br /><br />
              <div class="text-center">
                <img
                  class="rounded mx-auto d-block"
                  src="@/assets/grapeDoodle.png"
                /><br />
                ~ by doshMobile#0001
              </div>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingPower" class="accordion-header">
            <button
              aria-controls="collapsePower"
              aria-expanded="true"
              class="accordion-button collapsed"
              data-bs-target="#collapsePower"
              data-bs-toggle="collapse"
              type="button"
            >
              Power / Points
            </button>
          </h2>
          <div
            id="collapsePower"
            aria-labelledby="headingPower"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              You gain <strong>power</strong> based on the rankers above you, at
              your current ladder. The rate at which you gain power is also
              influenced by your bias and multi. Each bias increases the power
              gained, by one. Each multi multiplies that score (including the
              Bias) by its value.<br />
              You gain <strong>points</strong> based on your current power.
              <br /><br />
              power += (rank + bias - 1) * multi;<br />
              points += power;
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingUpgrades" class="accordion-header">
            <button
              aria-controls="collapseUpgrades"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapseUpgrades"
              data-bs-toggle="collapse"
              type="button"
            >
              Bias / Multi
            </button>
          </h2>
          <div
            id="collapseUpgrades"
            aria-labelledby="headingUpgrades"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              You gain multi and bias by sacrificing all your power and/or
              points. The cost is based on your current ladder.<br />
              For <strong>bias</strong> you need to sacrifice your points and
              for <strong>multi</strong> you reset with power, but also
              sacrifice all your points and bias. <br /><br />
              upgradeCost = (currentLadder + 1) ^ (level);
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingPromote" class="accordion-header">
            <button
              aria-controls="collapsePromote"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapsePromote"
              data-bs-toggle="collapse"
              type="button"
            >
              Promoting to the next Ladder
            </button>
          </h2>
          <div
            id="collapsePromote"
            aria-labelledby="headingPromote"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              You can promote to the next ladder, once there a few criteria
              fulfilled.<br />
              <ul>
                <li>You need to be <strong>rank 1</strong>.</li>
                <li>
                  You need to have reached the base point requirement or more.
                </li>
                <li>
                  You need enough people at your ladder. The minimum amount to
                  promote is 10, but it increases from 11 and onward by 1.
                </li>
                <li>
                  You need a big enough lead to your pursuer. This lead is
                  generally reached after being <strong>~30sec</strong> in front
                  of them. (people can throw vinegar at you during this time)
                </li>
                <li>
                  You need to press the <strong>promote</strong> button, that
                  will appear instead of the throw vinegar button.
                </li>
              </ul>
              Promotion leaves behind a body, that doesn't gain power or points,
              for the rest of the ladder to marvel at.<br />
              If you are at the last ladder with all your peers, the ability to
              promote turns into an <strong>Asshole-Button</strong>, which, if
              enough people press it:
              <br />
              <h4 style="text-align: center">
                <strong><u>RESETS THE GAME!</u></strong>
              </h4>
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingGrapes" class="accordion-header">
            <button
              aria-controls="collapseGrapes"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapseGrapes"
              data-bs-toggle="collapse"
              type="button"
            >
              Sour Grapes / Vinegar
            </button>
          </h2>
          <div
            id="collapseGrapes"
            aria-labelledby="headingGrapes"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              You can gain sour grapes in 2 ways.
              <ul>
                <li>
                  <strong>Being last</strong> in a ladder that has already
                  filled up, gains you 2 sour grape every second.
                </li>
                <li>
                  <strong>Getting passed</strong> by someone else, while having
                  more than 1 multi, also rewards you with 1 sour grape.
                </li>
              </ul>
              Also, you gain vinegar based on your current grape-count. (if you
              are not #1)<br />
              This vinegar can be used to throw onto the top person, if they are
              rank 1. If they have more vinegar than you, you just subtract your
              amount of vinegar from theirs, but if you throw more than they can
              handle, you force them to buy a multi or reset their points to 0
              if they can't afford it. The person on the top spot also loses
              0.25% of their vinegar count every second.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingAutoPromote" class="accordion-header">
            <button
              aria-controls="collapseAutoPromote"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapseAutoPromote"
              data-bs-toggle="collapse"
              type="button"
            >
              Auto-Promotion
            </button>
          </h2>
          <div
            id="collapseAutoPromote"
            aria-labelledby="headingAutoPromote"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              There is a way that you can auto-promote, which unlocks from
              Ladder 1 onward. This feature, can be bought using your
              hard-earned grapes and completely bypasses the criteria, that you
              need a big enough lead to promote.<br />
              Also, it's completely server-sided, so once you have it, no amount
              of vinegar can hurt you this ladder, even if you are not online.
              The cost of this feature gets cheaper, depending on how far behind
              you are on your current climb.<br />
              Sadly, this feature is not available on the last ladder, which
              means
              <strong>no one</strong>, can escape the grapes of their
              competitors.
            </div>
          </div>
        </div>
        <div class="accordion-item">
          <h2 id="headingGuideLadder1" class="accordion-header">
            <button
              aria-controls="collapseGuideLadder1"
              aria-expanded="false"
              class="accordion-button collapsed"
              data-bs-target="#collapseGuideLadder1"
              data-bs-toggle="collapse"
              type="button"
            >
              How do I escape from Ladder #1 - a guide by Zilvarro#7361 (Version
              2.00)
            </button>
          </h2>
          <div
            id="collapseGuideLadder1"
            aria-labelledby="headingGuideLadder1"
            class="accordion-collapse collapse"
          >
            <div class="accordion-body">
              <p>
                This guide is aimed at new players of MoreFairGame, who already
                read the rest of help section and clicked around in the game a
                bit, but are struggling to find a strategy to conquer the
                monstrosity that the very first Ladder of the game tends to be.
                This guide does not cover Grapes and Vinegar in detail, but to
                help out everyone stuck in Ladder 1 including yourself, it is a
                good idea to regularly throw Vinegar at afk players who stay at
                the top for a few minutes without promoting, especially so if
                their Power is high.
              </p>
              <p>
                Leaving the first Ladder is far easier and faster right after
                the game resets for everyone. So you could just wait for that
                reset, but why not use the time to learn the mechanics and
                strategies of the game, and see how you can fare against the
                monster that Ladder 1 is right now until the next reset comes
                around.
              </p>
              <p>
                The key takeaway if you do not want to read through the entire
                guide is to bring some patience, focus on buying Multis first,
                then focus on buying Bias to keep yourself at the bottom of the
                ranking and then finally just let the game run and hope you make
                it to first place and can hit the promote button. <br /><br />
              </p>
              <h2><strong>Basic Strategy</strong></h2>
              <p>The basic strategy boils down to four steps:</p>
              <ol>
                <li>Buy Multis</li>
                <li>Buy Bias</li>
                <li>Run for the Top</li>
                <li>Promote Button</li>
              </ol>
              <h3><strong>Step 1: Buy Multis</strong></h3>
              <p>
                In this step you buy Multis to improve your Power gain. Every
                Multi you get increases the multiplier for the Power you gain
                each second. That sounds great, so should we just keep buying
                more Multis whenever they appear? No, since each time you buy a
                Multi it completely resets your Power, Bias and Points, you
                should at some point commit to your current level of Multi and
                stop buying more. The decision to stick with your Multi is what
                ends this first step of the basic strategy. While building up
                your Multis you can also buy some Bias here and there, but it
                tends to not matter that much when there are many people in the
                Ladder.
              </p>
              <h3><strong>Step 2: Buy Bias</strong></h3>
              <p>
                In this step, you buy Bias. Intuitively, the effect of Bias is
                that each Bias lets you pretend to be one spot lower on the
                Ladder than you actually are. This is important because the game
                is balanced to give the players at the bottom of the Ladder more
                Power each second to help them catch up. However, a few points
                of Bias barely make a difference when there are hundreds of
                people in the Ladder. Does that mean you should neglect Bias?
                No, there is an easily overlooked second advantage that Bias can
                give you: Every time you buy a Bias, your Points will be reset
                to zero but your Power stays the same. This means, each time you
                buy a Bias, you are sent back to the very bottom to the
                leaderboard. That is a great thing, because it lets you
                accumulate Power faster than if you stayed at a higher rank.
                Therefore, in this step, you want to buy some Bias spaced out in
                a way to let you stay around the bottom parts of the Ladder.
                Power is very important in this game, because having higher
                Power than your competition is what will allow you to eventually
                surpass them in the ranking. However, ultimately Points are what
                makes you rise to the very top of the Ladder, so you should not
                keep resetting them by buying Multis or Bias forever. Instead,
                just like previously with Multis, this step finishes by
                committing to your current level of Bias.
              </p>
              <h3><strong>Step 3: Run for the Top</strong></h3>
              <p>
                At this point, you are done buying and all that is left to do
                for now is to watch yourself rise the Ladder and hope that you
                will make it to first place eventually.
              </p>
              <h3><strong>Step 4: Promote Button</strong></h3>
              <p>
                The final step is to click the promote button and go on to the
                next Ladder. Shortly after you hit the first place, a promote
                button appears at the top center of the page. You want to click
                this button before the other players push you out of first place
                again or, even worse, use their Vinegar to send you all the way
                back to the bottom of the Ladder.
              </p>
              <p>
                The Basic Strategy gives you a solid framework that you can
                build your own strategies on. The next section will give you a
                detailed example strategy that you could try out. It is of
                course entirely possible to make the Basic Strategy work by just
                relying on your intuition instead of following a detailed plan.
                <br /><br />
              </p>
              <h2><strong>Detailed Example Strategy</strong></h2>
              <h3><strong>Step 1: Buy Multis</strong></h3>
              <p>
                Start out by buying Multis whenever possible. Meanwhile, keep an
                eye on the levels of Power that the people at the first place
                tend to have. You stop buying Multis once the price for the next
                Multi would be close to or exceed half the average Power of
                first place you observed lately.
              </p>
              <h3><strong>Step 2: Buy Bias</strong></h3>
              <p>
                Start out by buying Bias whenever possible. Meanwhile, keep an
                eye on the number of Points that the people at the first place
                tend to have. You stop buying Bias once the price for the next
                Bias would be close to or exceed 20% of the current score of
                first place.
              </p>
              <h3><strong>Step 3: Run for the Top</strong></h3>
              <p>
                While aiming for the top you can compare your Power to the Power
                of those around you. If almost all the people around you have
                lower Power than you, everything is working well.If you still
                find yourself stuck in the ranking with insufficient Power to
                climb up, you could just buy another Bias and try again.
              </p>
              <h3><strong>Step 4: Promote Button</strong></h3>
              <p>
                Make absolutely sure to be around when you get close to the top.
                Just hit the promote button as soon as possible to give other
                players the least amount of time to mess with you. Being active
                right when promotion time hits is the only reliable way to
                ensure a safe promotion. Optionally, if you still fear being hit
                with Vinegar, you could chat a bit in the in-game chat while
                waiting to hit first place. Experienced players will likely see
                your name in chat and realize that using Vinegar on you would be
                a waste as you are going to promote anyways.
              </p>
              <p>
                Note that this Example Strategy favors simplicity over perfect
                efficiency. Trying to time your Multi and Bias purchases
                optimally and deciding when it is the best time to stop buying
                can be very challenging! While the guide is mainly focused on
                the first Ladder, most of the concepts introduced here are still
                very valid when tackling the rest of the game. <br /><br />
              </p>
              <h2><strong>Where to go from here</strong></h2>
              <p>
                The official discord server for the game is super helpful. You
                will be able to be notified when the next reset comes and you
                can try to climb the Ladders for real. Also you can find a
                userscript on the discord server that estimates the times to
                overtake other players or make it to the top and provides many
                more quality of life features for the game. Do not be afraid to
                ask on discord or the ingame-chat for help whenever you have
                questions.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.scroller {
  max-height: calc(100vh - 56px);
  overflow: auto !important;
}

.accordion-item {
  color: var(--text-color);
}
</style>
