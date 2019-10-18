import { Message } from '../message/message'

export class User {
    user_id: number;
    username: String;
    password: String;
    email: String;
    messages: Array<Message>
    jwt: String;

    constructor(un: string, pw: string, email: string, 
        id?: number, messages?: Array<Message>, token?: string)
    {
        this.user_id = id || 0;
        this.username = un;
        this.password = pw;
        this.email = email;
        this.messages = messages || [];
        this.jwt = token || "";
    }
}
