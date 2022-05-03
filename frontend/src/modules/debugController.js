import store from "../store";

export function enableDebug() {
  store.commit({
    type: "options/updateOption",
    option: store.getters["options/getOption"]("enableDebugFeatures"),
    payload: { value: true },
  });
  console.log("Debug features enabled");
}

export function disableDebug() {
  store.commit({
    type: "options/updateOption",
    option: store.getters["options/getOption"]("enableDebugFeatures"),
    payload: { value: false },
  });
  console.log("Debug features disabled");
}
