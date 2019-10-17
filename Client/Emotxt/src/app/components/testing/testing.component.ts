import { Component, OnInit } from '@angular/core';
import { WebSocketAPI } from 'src/app/models/websocketapi/web-socket-api';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {

  webSocketAPI: WebSocketAPI;
 greeting: any;
  name: string;
  constructor() { }

  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new TestingComponent());
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
