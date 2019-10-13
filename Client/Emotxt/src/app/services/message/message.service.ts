import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ConnectionService } from '../connection/connection.service';
import { map } from 'rxjs/operators';

const CHAT_URL = 'ws://echo.websocket.org/';

export interface Message {
  author: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})

export class MessageService {
  public messages: Subject<Message>;

  constructor(wsService: ConnectionService) {
    this.messages = wsService.connect(CHAT_URL).pipe(
      map((response: MessageEvent): Message => {
        const data = JSON.parse(response.data);
        return {
          author: data.author,
          message: data.message
        };
      })) as Subject<Message>;
   }
}
