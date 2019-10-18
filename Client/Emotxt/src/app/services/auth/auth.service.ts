import { Injectable } from '@angular/core';
import { ConnectionService } from '../connection/connection.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Principal } from '../../models/principal/principal';


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
    
    let creds = { "username": username, "password": password };
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log('In the login before request');
    // console.log(creds);
    // console.log(httpHeaders);

    this.conn.sendPost('auth', JSON.stringify(creds), httpHeaders).subscribe(resp => {
      console.log("In auth service resp");
      console.log(resp);
      
      let principal = new Principal(
        resp.body.id,
        resp.body.username,
        resp.body.role
      );
      principal.accountId = resp.body.account_id;
      principal.state = resp.body.state;
      principal.country = resp.body.country;

      console.log(principal);
      
      console.log(resp.headers.get('Authorization'));
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
