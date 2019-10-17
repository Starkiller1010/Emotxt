import { Component, OnInit, ViewChild } from '@angular/core';
import { ConnectionService } from './services/connection/connection.service';
import { MessageService } from './services/message/message.service';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ConnectionService, MessageService]
})
export class AppComponent implements OnInit {

  @ViewChild(NavbarComponent) nav: NavbarComponent;

  constructor() {}

  onCollapse() {
    this.nav.onCollapse();
  }

  ngOnInit() {
  }
}
