import { Component, OnInit } from '@angular/core';
import { WebSocketAPI } from 'src/app/models/websocketapi/web-socket-api';
import { TestingComponent } from '../testing/testing.component';

@Component({
  selector: 'app-message-box',
  templateUrl: './message-box.component.html',
  styleUrls: ['./message-box.component.css']
})
export class MessageBoxComponent implements OnInit {


  constructor(private testing: TestingComponent) { }

  ngOnInit() {
  }

  onSend = () => {
    const message = document.getElementById('text-box');
    this.testing.sendMessage(message['value']);
  }

}
