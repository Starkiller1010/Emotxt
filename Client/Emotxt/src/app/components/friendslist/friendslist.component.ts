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
      'Authorization': localStorage.getItem('emo-jwt')
    });

    console.log('In the friend component before get friends request');
    // console.log(creds);
    // console.log(httpHeaders);

    return this.conn.sendGet(`accounts/${this.authService.currentUserValue.accountId}/friends`, httpHeaders).subscribe(resp => {
      console.log("In auth service resp");
      console.log(resp);

      resp.body.forEach(element => {
        this.friends.push(element);
      });
    })

  }

}
