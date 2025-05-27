import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserApiService, CreateUserRequest, LoginRequest } from '../../services/user-api.service';
import {AuthService} from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email = '';
  password = '';
  error = '';


  constructor(
    private api: UserApiService,
    private router: Router,
    private auth: AuthService
  ) {}

  login() {
    const data: LoginRequest = {
      email: this.email,
      password: this.password
    };

    this.api.login(data).subscribe({
      next: user => {
        this.auth.login(user.role);
        this.router.navigate(['/home']);
      },
      error: () => this.error = 'Login failed'
    });

  }

}
