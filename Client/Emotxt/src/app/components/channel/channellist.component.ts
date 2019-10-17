import { Component, OnInit, ViewChild } from '@angular/core';
import { TestingComponent } from '../testing/testing.component';
import { MessageBoxComponent } from '../message-box/message-box.component';

@Component({
  selector: 'app-channellist',
  templateUrl: './channellist.component.html',
  styleUrls: ['./channellist.component.css']
})
export class ChannellistComponent implements OnInit {

  @ViewChild(TestingComponent) tester: TestingComponent;
  @ViewChild(MessageBoxComponent) messenger: MessageBoxComponent;


  constructor() { }

  ngOnInit() {
  }

}
