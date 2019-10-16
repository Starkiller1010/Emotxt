import { Component, OnInit } from '@angular/core';
import { WebSocketAPI } from './models/websocketapi/web-socket-api';
import { ConnectionService } from './services/connection/connection.service';
import { MessageService } from './services/message/message.service';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ConnectionService, MessageService]
})
export class AppComponent implements OnInit {

  nav: NavbarComponent;

  constructor() {}

  webSocketAPI: WebSocketAPI;
  greeting: any;
  name: string;

  onCollapse() {
    console.log('Collapsing...');
    this.nav.onCollapse();
  }

  ngOnInit() {
    this.nav = new NavbarComponent();
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
