import { User } from '../user/user';
import { Channel } from '../channel/channel';

export class Account {
    id: number;
    user: User;
    country: string;
    state: string;
    city: string;
    status: string;
    bio: string;
    friends: Array<Account>;
    subscriptions: Array<Channel>;

    constructor(user: User, id?: number, country?: string, bio?: string, state?: string, city?: string,
                status?: string, friends?: Array<Account>, subscriptions?: Array<Channel>) {
        this.user = user;
        this.id = id || 0;
        this.country = country || ' ';
        this.state = state || ' ';
        this.city = city || ' ';
        this.status = status || 'OFFLINE';
        this.bio = bio || ' ';
        this.friends = friends || [];
        this.subscriptions = subscriptions || [];
    }
}
