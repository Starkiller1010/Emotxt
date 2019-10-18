import { Message } from '../message/message'
import { Account } from '../account/account'
import { User } from '../user/user';

export class Channel {
    id: number;
    messages: Array<Message>;
    members: Array<User>;

    constructor(id?: number, messages?: Array<Message>, members?: Array<User>)
    {
        this.id = id;
        this.messages = messages;
        this.members = members;
    }
}
