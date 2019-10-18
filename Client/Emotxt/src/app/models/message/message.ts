import { Observer } from 'rxjs';
import { User } from '../user/user';

export class Message {
  body: string;
  author: string;
  destination: string;

  constructor(private msg: string, private aut: string, des: string) {
    this.author = aut;
    this.body = msg;
    this.destination = des;
  }
}
