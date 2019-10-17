import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { TestingComponent } from 'src/app/components/testing/testing.component';
import { Message } from '../message/message';

export class WebSocketAPI {

  webSocketEndPoint = 'http://localhost:8080/Emotxt/socket';
  topic = '/topic/hello';

  stompClient: any;
  appComponent: TestingComponent;

    constructor(appComponent: TestingComponent) {
        this.appComponent = appComponent;
    }

    _connect() {
        console.log('Initialize WebSocket Connection');
        const ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe(this.topic, (sdkEvent) => {
            this.onMessageReceived(sdkEvent);
            });
        }, this.errorCallBack);
    }

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log('Disconnected');
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error) {
        console.log('errorCallBack -> ' + error);
        setTimeout(() => {
          if (this !== null) {
               this._connect(); }
        }, 5000);
    }

 /**
  * Send message to sever via web socket
  * @param `message` description
  */
    _send(message: string, author: string) {
        console.log('sending message api via web socket');
        const letter = new Message(message, author);
        this.stompClient.send('/app/hello', {}, JSON.stringify(letter));
    }

    onMessageReceived(message) {
      console.dir(message);
      console.log('Message Received from Server :: ' + message.body);
      const content = JSON.parse(message.body);
      this.appComponent.handleMessage(content['body'], content['author']);
    }
}
