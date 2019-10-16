import { Component, OnInit } from '@angular/core';
import { WebSocketAPI } from './models/websocketapi/web-socket-api';
import { ConnectionService } from './services/connection/connection.service';
import { MessageService } from './services/message/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ConnectionService, MessageService]
})
export class AppComponent implements OnInit {
  // constructor(private chatService: MessageService) {
  //   chatService.messages.subscribe(msg => {
  //     console.log('Response from websocket: '+ msg);
  //   });
  // }

  // private message = {
  //   author: 'tutorialedge',
  //   message: 'this is a test message'
  // };

  // sendMsg = () => {
  //   console.log('New message from client to websocket...');
  //   this.chatService.messages.next(this.message);
  //   this.message.message = '';
  // }
  webSocketAPI: WebSocketAPI;
  greeting: any;
  name: string;

  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new AppComponent());
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage() {
    this.webSocketAPI._send(this.name);
  }

  handleMessage(message) {
    this.greeting = message;
  }
}
