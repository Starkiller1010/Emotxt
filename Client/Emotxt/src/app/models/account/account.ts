import { User } from '../user/user'
import { Message } from '../message/message'
import { Channel } from '../channel/channel';

export class Account {
    id: number;
    user: User;
    country: String;
    state: String;
    city: string;
    status: String;
    bio: String;
    friends: Array<Account>;
    subscriptions: Array<Channel>

    constructor(user: User, id?: number, country?: string, state?: string, city?: string,
                status?: string, bio?: string, friends?: Array<Account>, subscriptions?: Array<Channel>)
    {
        this.user = user;
        this.id = id || 0;
        this.country = country || "";
        this.state = state || "";
        this.city = city || "";
        this.status = status || "OFFLINE";
        this.bio = bio || "";
        this.friends = friends || [];
        this.subscriptions = subscriptions || [];
    }
}