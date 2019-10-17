import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebSocketAPI } from 'src/app/models/websocketapi/web-socket-api';
import { Message } from 'src/app/models/message/message';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit, OnDestroy {

  webSocketAPI: WebSocketAPI;
  name: string;
  constructor() { }

  ngOnDestroy(): void {
   // this.disconnect();
  }
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(this);
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage(msg: string) {
    this.webSocketAPI._send(msg, this.name);
  }

  handleMessage(message: string, author: string) {
    console.log(message);
    console.log(author);
    const messages = document.getElementById('message-list');

    const body = document.createElement('div');
    const padding = document.createElement('div');
    const content = document.createElement('div');

    content.classList.add('col-md-12');
    body.classList.add('col-md-5');
    padding.classList.add('col-md-7');


    const incoming = document.createElement('p');
    body.appendChild(incoming);

    console.log(`author: ${author} | name: ${this.name}`);

    if (author === this.name) {
      incoming.style.backgroundColor = 'lightblue';
      // incoming.style.textAlign = 'right';
      content.appendChild(padding);
      content.appendChild(body);
    } else {
      incoming.style.backgroundColor = 'lightgrey';
      content.appendChild(body);
      content.appendChild(padding);
    }
    // incoming.style.padding = '10rem';
    incoming.style.textAlign = 'left';
    incoming.style.borderRadius = '12%';

    incoming.innerText =
    // tslint:disable-next-line: max-line-length
    (`At: ${new Date().getMonth() + 1}/${new Date().getDay()}/${new Date().getFullYear()}: ${new Date().getHours()}:${new Date().getMinutes()}
    By: ${author}
    Message: \n"${message}"`);
    messages.appendChild(content);
  }
}
