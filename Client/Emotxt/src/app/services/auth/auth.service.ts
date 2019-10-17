import { Injectable } from '@angular/core';
import { ConnectionService } from '../connection/connection.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Principal } from '../../models/principal/principal';
import { environment as env } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<Principal>;
  currentUser$: Observable<Principal>;

  constructor(private conn: ConnectionService, private router: Router) { 
    this.currentUserSubject = new BehaviorSubject<Principal>(null);
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  get currentUserValue(): Principal {
    return this.currentUserSubject.value;
  }

  doLogin = (username: string, password: string) => {
    let creds = { username, password };
    this.conn.sendPost('auth', creds).subscribe(resp => {
      let principal = resp.body as Principal;
      principal.jwt = resp.headers.get('Authorization');
      localStorage.setItem('emo-jwt', resp.headers.get('Authorization'));
      this.currentUserSubject.next(principal);

      console.log('Login Successful');
      console.log('navigating to dashboard...');
      this.router.navigate(['landing']);
    }, err => {
      console.error(err);
      this.router.navigate(['login'])
    });
  }

  doRegister = () => {
    this.conn.sendGet('').subscribe(resp => {

    }, err => {

    });
  }
}
