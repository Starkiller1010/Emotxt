import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavBarHelperService } from 'src/app/services/navbar/nav-bar-helper.service';
import { Subscription, Observer } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';
import { User } from 'src/app/models/user/user';
import { Principal } from 'src/app/models/principal/principal';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isCollapsed = false;
  currentUser$: Subscription;
  currentPrincipal;

  constructor(private router: Router, private helper: NavBarHelperService, private auth: AuthService) {
    this.currentUser$ = auth.currentUser$.subscribe(principal => this.currentPrincipal = principal);
  }

  ngOnInit() {
  }

  onLogout = () => {
    this.auth.doLogout();
    this.router.navigate(['home']);
  }

  onCollapse = () => {
    if (!this.isCollapsed) {
    document.getElementById('sidebar').classList.add('inactive');
    } else {
    document.getElementById('sidebar').classList.remove('inactive');
    }
    this.isCollapsed = !this.isCollapsed;
  }

}
