import { Component, OnInit } from '@angular/core';
import { TestingComponent } from '../testing/testing.component';
import { Router } from '@angular/router';
import { Channel } from 'src/app/models/channel/channel';
import { Subject, Observable, Subscription, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-messageboard',
  templateUrl: './messageboard.component.html',
  styleUrls: ['./messageboard.component.css']
})
export class MessageboardComponent implements OnInit {

  currChannelSub: BehaviorSubject<Channel>;
  constructor(private list: TestingComponent, private router: Router) {}

  ngOnInit() {
  }

  onClick = (e: Event) => {

    console.log(e);
    console.dir(e.target);
   // this.currChannelSub.next(e.target as Channel);
   // this.router.navigate(['channel']);
  }

}
