import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { environment as env } from '../../../environments/environment';
import { User } from '../../models/user/user';
import { Account } from '../../models/account/account';
import { ConnectionService } from '../../services/connection/connection.service';
import { HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  submitted: boolean;

  constructor(private formBuilder: FormBuilder, private router: Router, private conn: ConnectionService) {
      console.log("Instantiating RegisterComponent...");
      console.log("RegisterComponent instantiation complete");
   }

  ngOnInit() {
    console.log("ngInit RegisterComponent...");
    this.submitted = false;
    this.registerForm = this.formBuilder.group({
      username: ['', [ /*Validators.pattern('^[\w[^0-9_]]\w+'),*/ Validators.minLength(6), Validators.required] ],
      password: ['',[ Validators.minLength(8), Validators.required] ],
      email: ['', [ EmailValidator, Validators.required] ],
      country: [''],
      state: [''],
      bio: [''],
    })
  }

  get fields() {
    return this.registerForm.controls;
  }
  
  onSubmit = () => {
    console.log("Submitted");
    this.submitted = true;
    if(this.registerForm.invalid) return;
    console.log("Valid submission");
    
    let headers = new HttpHeaders(
      {'Content-Type': 'application/json'}
    );
    
    // Create user based on provided values.
    let user = new User(this.fields.username.value, this.fields.password.value, this.fields.email.value);
    let acct = new Account(this.fields.country.value, this.fields.state.value, this.fields.bio.value);

    console.log("Sending POST");
    this.conn.sendPost('users', JSON.stringify(user), headers).subscribe(resp =>
      {
        if(resp.status == 201 || resp.status == 200) {
          this.conn.sendPost('accounts', JSON.stringify(acct), headers).subscribe (resp => {
              console.log("Sent POST to accounts");
              if(resp.status == 200 || resp.status == 201) {
                console.log("Successfully registered account and user.")
              }
              else console.log("Failed to account.");
          });
        } else console.log("Failed to user.");
      });

    // Send user to UserController and await a response before sending the Account to the account controller.    
    
  }
}
