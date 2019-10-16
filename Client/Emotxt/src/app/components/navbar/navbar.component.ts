import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isCollapsed = false;

  constructor() { }

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
