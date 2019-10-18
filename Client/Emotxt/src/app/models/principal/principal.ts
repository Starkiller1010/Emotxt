export class Principal {
    id: number;
    accountId: number;
    username: string;
    role: string;
    country: string;
    state: string;
    friends: [];
    channels: [];

    

    constructor(id: number, un: string, role: string) {
        this.id = id;
        this.username = un;
        this.role = role;  
    }
}
