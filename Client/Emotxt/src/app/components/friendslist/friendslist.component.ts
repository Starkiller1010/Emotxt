import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ConnectionService } from '../../services/connection/connection.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-friendslist',
  templateUrl: './friendslist.component.html',
  styleUrls: ['./friendslist.component.css']
})
export class FriendslistComponent implements OnInit {

  friends = [];
  constructor(private authService: AuthService, private conn: ConnectionService) { }

  ngOnInit() {

    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      // tslint:disable-next-line: object-literal-key-quotes
      'Authorization': localStorage.getItem('emo-jwt')
    });

    console.log('In the friend component before get friends request');
    const accountId = this.authService.currentUser$.subscribe(user => user.accountId );

    return this.conn.sendGet(`accounts/${accountId}/friends`, httpHeaders).subscribe(resp => {
      console.log('In auth service resp');
      console.log(resp);
      const temp = resp.body as object[];

      temp.forEach(element => {
        this.friends.push(element);
      });
    });

  }

}
