<template>
  <div class="game-view container">
    <div
      v-if="!hideChat"
      class="row py-0"
      style="height: 100%; max-height: 100%"
    >
      <div class="col-12 col-md-7">
        <div class="container py-1 px-3">
          <LadderWindow />
          <InfoBox />
        </div>
      </div>
      <div class="col-12 col-md-5">
        <ChatWindow />
      </div>
    </div>
    <div
      v-if="hideChat"
      class="row py-0"
      style="height: 100%; max-height: 100%"
    >
      <div class="container py-1 px-3">
        <LadderWindow />
        <InfoBox />
      </div>
    </div>
  </div>
</template>

<script setup>
// @ is an alias to /src
import LadderWindow from "@/ladder/components/LadderWindow";
import ChatWindow from "@/chat/components/ChatWindow";
import InfoBox from "@/ladder/components/InfoBox";

import { useStore } from "vuex";
import { computed } from "vue";

const store = useStore();
const hideChat = computed(() =>
  store.getters["options/getOptionValue"]("hideChat")
);
</script>

<style lang="scss" scoped>
@import "../styles/styles";

.game-view {
  max-width: 100vw;

  @media (max-width: 768px) and (min-width: 400px) {
    height: calc(200vh - 56px);
  }
  @media (max-width: 400px) {
    height: calc(300vh - 56px);
  }
  @media (min-width: 768px) {
    height: calc(100vh - 56px);
  }

  div {
    max-height: 100%;

    &.container {
      height: 100%;
    }
  }
}

.navbar {
  height: 56px;
}
</style>
