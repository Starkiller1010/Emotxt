export class Principal {
    id: number;
    username: string;
    role: string;
    jwt: string;

    constructor(id: number, un: string, role: string, token: string) {
        this.id = id;
        this.username = un;
        this.role = role;
        this.jwt = token;
    }
}
