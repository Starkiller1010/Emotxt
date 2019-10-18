import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private authService: AuthService) { }

  username = this.authService.currentUser$.subscribe(user => user.username );

  ngOnInit() {


  }

}
