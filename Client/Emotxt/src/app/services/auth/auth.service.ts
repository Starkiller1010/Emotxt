import { Injectable } from '@angular/core';
import { ConnectionService } from '../connection/connection.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private conn: ConnectionService) { }

  doLogin = () => {
    this.conn.sendGet('').subscribe(resp => {

    }, err => {

    });
  }

  doRegister = () => {
    this.conn.sendGet('').subscribe(resp => {

    }, err => {

    });
  }
}
