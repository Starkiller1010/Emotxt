import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavBarHelperService } from 'src/app/services/navbar/nav-bar-helper.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isCollapsed = false;

  constructor(private router: Router, private helper: NavBarHelperService) { }

  ngOnInit() {
  }

  onCollapse = () => {
    if (!this.isCollapsed) {
    document.getElementById('sidebar').classList.add('inactive');
    } else {
    document.getElementById('sidebar').classList.remove('inactive');
    }
    this.isCollapsed = !this.isCollapsed;
    console.log(`is now: ${this.isCollapsed}`);
  }

}
