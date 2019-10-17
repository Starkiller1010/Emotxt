import { Injectable } from '@angular/core';
import { ConnectionService } from '../connection/connection.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Principal } from '../../models/principal/principal';
import { environment as env } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<Principal>;
  currentUser$: Observable<Principal>;

  constructor(private conn: ConnectionService) { 
    this.currentUserSubject = new BehaviorSubject<Principal>(null);
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  get currentUserValue(): Principal {
    return this.currentUserSubject.value;
  }

  doLogin = (username: string, password: string) => {
    let creds = { username, password };
    this.conn.sendPost('auth', creds).subscribe(resp => {
      console.log(resp);
    }, err => {

    });
  }

  doRegister = () => {
    this.conn.sendGet('').subscribe(resp => {

    }, err => {

    });
  }
}
