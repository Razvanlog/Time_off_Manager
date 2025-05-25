import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserApiService, CreateUserRequest } from '../../services/user-api.service';
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

  constructor(private api: UserApiService, private router: Router) {}

  login() {
    const data: CreateUserRequest = { name: '', email: this.email, password: this.password };
    this.api.login(data).subscribe({
      next: user => {
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigate(['/home']);
      },
      error: () => this.error = 'Login failed. Check your credentials.'
    });
  }
}
