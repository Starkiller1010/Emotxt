import { Observer } from 'rxjs';
import { User } from '../user/user';

export class Message {
  body: string;
  author: string;

  constructor(private msg: string, private aut: string) {
    this.author = aut;
    this.body = msg;
  }
}
