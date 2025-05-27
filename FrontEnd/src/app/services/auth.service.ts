import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  private _role$ = new BehaviorSubject<string>('');

  readonly isLoggedIn$ = this._isLoggedIn$.asObservable();
  readonly role$ = this._role$.asObservable();

  constructor() {
    if (typeof window !== 'undefined') {
      const user = localStorage.getItem('user');
      try {
        const parsed = user ? JSON.parse(user) : null;
        if (parsed?.role) {
          this._isLoggedIn$.next(true);
          this._role$.next(parsed.role);
        }
      } catch (e) {
        localStorage.removeItem('user');
      }
    }
  }


  login(role: string): void {
    this._isLoggedIn$.next(true);
    this._role$.next(role);

    if (typeof window !== 'undefined') {
      localStorage.setItem('user', JSON.stringify({ role }));
    }
  }

  logout(): void {
    if (typeof window !== 'undefined') {
      localStorage.removeItem('user');
    }
    this._isLoggedIn$.next(false);
    this._role$.next('');
  }
}
