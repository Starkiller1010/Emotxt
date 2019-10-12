import { Component } from '@angular/core';
import { ConnectionService } from './services/connection/connection.service';
import { MessageService } from './services/message/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ConnectionService, MessageService]
})
export class AppComponent {
  constructor(private chatService: MessageService) {
    chatService.messages.subscribe(msg => {
      console.log('Response from websocket: '+ msg);
    });
  }

  private message = {
    author: 'tutorialedge',
    message: 'this is a test message'
  };

  sendMsg = () => {
    console.log('New message from client to websocket...');
    this.chatService.messages.next(this.message);
    this.message.message = '';
  }
}
