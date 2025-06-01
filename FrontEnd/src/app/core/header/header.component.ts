import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

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
export class HeaderComponent implements OnInit, OnDestroy {
  isLoggedIn = false;
  role: string | null = null;

  private isLoggedInSubscription: Subscription | undefined;
  private roleSubscription: Subscription | undefined;

  constructor(
    public auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedInSubscription = this.auth.isLoggedIn$.subscribe(value => {
      this.isLoggedIn = value;
      console.log('HeaderComponent: isLoggedIn updated to', this.isLoggedIn);
    });

    this.roleSubscription = this.auth.role$.subscribe(value => {
      this.role = value;
      console.log('HeaderComponent: role updated to', this.role);
    });
  }

  ngOnDestroy(): void {
    if (this.isLoggedInSubscription) {
      this.isLoggedInSubscription.unsubscribe();
    }
    if (this.roleSubscription) {
      this.roleSubscription.unsubscribe();
    }
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
