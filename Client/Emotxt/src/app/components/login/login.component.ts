import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { FriendslistComponent } from '../friendslist/friendslist.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    console.log('In the Login constructor');
   }

  ngOnInit() {
    console.log('Initializing the firelds for LoginComponent.');
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    console.log('LoginComponent value initialization complete.');
  }

  get fields() {
    return this.loginForm.controls;
  }

  onSubmit = () => {
    this.submitted = true;
    if(this.loginForm.invalid) return;

    this.authService.doLogin(this.fields.username.value, this.fields.password.value);

  }

}
