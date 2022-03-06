import SockJs from "sockjs-client";
import Stomp from "stomp-websocket";
import Cookies from "js-cookie";

export class StompClient {
  constructor() {
    let isInDevelop = process.env.NODE_ENV === "development";
    let connection = isInDevelop
      ? "http://localhost:8080/fairsocket"
      : "/fairsocket";
    let socket = SockJs(connection);
    this.subscribeMap = new Map();
    this.stompClient = Stomp.over(socket);
    if (!isInDevelop) this.stompClient.debug = null;
    this.promise;
  }

  connect(func) {
    this.stompClient.connect(
      {},
      () => {
        func(this);
      },
      async () => {
        await this.disconnect();
      }
    );
  }

  async disconnect() {
    if (this.stompClient !== null) this.stompClient.disconnect();
    console.log(
      "Currently disconnected, waiting 60sec before trying to reconnect..."
    );
    await new Promise((r) => setTimeout(r, 65000));
  }

  subscribe(destination, func) {
    let subscription = this.subscribeMap.get(destination);
    if (subscription) subscription.unsubscribe();
    subscription = this.stompClient.subscribe(
      destination,
      (message) => func(JSON.parse(message.body)),
      { uuid: Cookies.get("_uuid") }
    );
    this.subscribeMap.set(destination, subscription);
  }

  send(destination, content) {
    let data = { uuid: Cookies.get("_uuid") };
    if (!data.uuid) data.uuid = "";
    if (content) data.content = content;
    this.stompClient.send(destination, {}, JSON.stringify(data));
  }
}