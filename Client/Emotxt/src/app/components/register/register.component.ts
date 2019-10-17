import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  submitted: boolean;

  constructor(private formBuilder: FormBuilder, private router: Router) {
      console.log("Instantiating RegisterComponent...");
      console.log("RegisterComponent instantiation complete");
   }

  ngOnInit() {
    console.log("ngInit RegisterComponent...");
    this.submitted = false;
    this.registerForm = this.formBuilder.group({
      username: ['', [ Validators.pattern('^[\w[^0-9_]]\w+'), Validators.minLength(6), Validators.required] ],
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
    this.submitted = true;
    if(this.registerForm.invalid) return

  }
}
