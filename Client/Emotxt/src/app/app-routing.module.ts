import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TestingComponent } from './components/testing/testing.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FriendslistComponent } from './components/friendslist/friendslist.component';
import { MessageboardComponent } from './components/messageboard/messageboard.component';
import { AccountComponent } from './components/account/account.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'test', component: TestingComponent},
  {path: 'home', component: HomeComponent},
  {path: 'landing', component: LandingComponent},
  {path: 'message-board', component: MessageboardComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'friendslist', component: FriendslistComponent},
  {path: 'account', component: AccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
