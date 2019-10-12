import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { MessageboardComponent } from './components/messageboard/messageboard.component';
import { SidePanelComponent } from './components/side-panel/side-panel.component';
import { TabBarComponent } from './components/tab-bar/tab-bar.component';
import { MessageBoxComponent } from './components/message-box/message-box.component';
import { AccountComponent } from './components/account/account.component';
import { ChannellistComponent } from './components/channellist/channellist.component';
import { FriendslistComponent } from './components/friendslist/friendslist.component';
import { BlocklistComponent } from './components/blocklist/blocklist.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    MessageboardComponent,
    FriendslistComponent,
    BlocklistComponent,
    SidePanelComponent,
    TabBarComponent,
    MessageBoxComponent,
    AccountComponent,
    ChannellistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
