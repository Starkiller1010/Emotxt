import { Message } from '../message/message'

export class User {
    username: String;
    password: String;
    email: String;
    city: String;
    messages: Array<Message>
}
