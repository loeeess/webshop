import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from '../login-logout/authentication.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  returnUrl: String;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router, private authservice: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get formcontrols() { return this.loginForm.controls; }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    this.authservice.login(this.formcontrols.username.value, this.formcontrols.password.value)
      .pipe(first())
      .subscribe(data => {
        this.router.navigate(['/shop']);
      },
      error => {
        console.log("Something went wrong in the authservice!: " + error);
      });
  }
}
