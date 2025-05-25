import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserApiService, CreateUserRequest } from '../../services/user-api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  name = '';
  email = '';
  password = '';
  error = '';

  constructor(private api: UserApiService, private router: Router) {}

  signup() {
    const data: CreateUserRequest = { name: this.name, email: this.email, password: this.password };
    this.api.signup(data).subscribe({
      next: () => this.router.navigate(['/login']),
      error: err => this.error = 'Signup failed. Email may already be taken.'
    });
  }
}
