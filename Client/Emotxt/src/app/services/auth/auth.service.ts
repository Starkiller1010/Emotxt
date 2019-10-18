import { Injectable } from '@angular/core';
import { ConnectionService } from '../connection/connection.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Principal } from '../../models/principal/principal';
import { Account } from 'src/app/models/account/account';


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

  get currentUserValue() {
    return this.currentUser$;
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

      const temp_prin = resp.body as Principal;
      const principal = new Principal(
        temp_prin.id,
        temp_prin.username,
        temp_prin.role
      );
      const temp_account = resp.body as Account;
      principal.accountId =  temp_account.id;
      principal.state = temp_account.state;
      principal.country = temp_account.country;

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
