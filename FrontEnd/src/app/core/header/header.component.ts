import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    RouterModule
  ]
})
export class HeaderComponent {
  isLoggedIn = false;
  role = '';

  constructor(
    public auth: AuthService,
    private router: Router
  ) {
    this.auth.isLoggedIn$.subscribe(value => this.isLoggedIn = value);
    this.auth.role$.subscribe(value => this.role = value);
  }

  logout(): void {
    this.auth.logout();
    window.location.href = '/login';
  }


}
