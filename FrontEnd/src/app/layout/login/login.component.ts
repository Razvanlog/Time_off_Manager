import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserApiService, LoginRequest, UserResponse } from '../../services/user-api.service';
import { AuthService } from '../../services/auth.service';
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

  login(): void {
    if (!this.email || !this.password) {
      this.error = 'Email and password are required.';
      return;
    }
    const data: LoginRequest = { email: this.email, password: this.password };
    this.error = '';

    this.api.login(data).subscribe({
      next: (user: UserResponse) => {
        console.log('LoginComponent: API login successful, user data:', user);

        this.auth.login(user);

        console.log('LoginComponent: Navigating based on role:', user.role);
        if (user.role === 'ADMIN') {
          this.router.navigate(['/admin-dashboard']);
        } else if (user.role === 'EMPLOYEE') {
          this.router.navigate(['/home']);
        } else if (user.role === 'MANAGER') {
          this.router.navigate(['/review-requests']);
        }
        else {
          console.warn('LoginComponent: Unknown role or no role-specific route defined, navigating to /home.');
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        console.error('LoginComponent: API login failed', err);
        if (err.status === 401 || err.status === 403) {
          this.error = 'Invalid email or password.';
        } else if (err.error && typeof err.error === 'string' && err.error.includes("User not found")) {
          this.error = 'User not found with the provided email.';
        } else if (err.error && typeof err.error === 'string' && err.error.includes("Invalid password")) {
          this.error = 'Invalid password.';
        }
        else {
          this.error = 'Login failed. Please try again later or contact support.';
        }
      }
    });
  }
}
