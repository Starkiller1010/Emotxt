import { Message } from '../message/message'

export class User {
    id: number;
    username: String;
    password: String;
    email: String;
    messages: Array<Message>
    jwt: String;

    constructor(un: string, pw: string, email: string, 
        id?: number, messages?: Array<Message>, token?: string)
    {
        this.id = id || 0;
        this.username = un;
        this.password = pw;
        this.email = email;
        this.messages = messages || [];
        this.jwt = token || "";
    }
}
