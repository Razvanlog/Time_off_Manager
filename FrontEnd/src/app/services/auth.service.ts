import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserResponse } from '../services/user-api.service';

export interface StoredUser {
  role: string;
  email: string;
  name?: string;
  userId?: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  private _role$ = new BehaviorSubject<string | null>(null);
  private _currentUser$ = new BehaviorSubject<StoredUser | null>(null);

  readonly isLoggedIn$: Observable<boolean> = this._isLoggedIn$.asObservable();
  readonly role$: Observable<string | null> = this._role$.asObservable();
  readonly currentUser$: Observable<StoredUser | null> = this._currentUser$.asObservable();

  private readonly USER_STORAGE_KEY = 'timeOffAppUser';

  constructor() {
    if (typeof window !== 'undefined' && localStorage) {
      const storedUserString = localStorage.getItem(this.USER_STORAGE_KEY);
      if (storedUserString) {
        try {
          const parsedUser = JSON.parse(storedUserString) as StoredUser;
          if (parsedUser && parsedUser.role && parsedUser.email) {
            this._isLoggedIn$.next(true);
            this._role$.next(parsedUser.role);
            this._currentUser$.next(parsedUser);
          } else {
            localStorage.removeItem(this.USER_STORAGE_KEY);
          }
        } catch {
          localStorage.removeItem(this.USER_STORAGE_KEY);
        }
      }
    }
  }

  login(userResponse: UserResponse): void {
    if (!userResponse || !userResponse.email || !userResponse.role) {
      return;
    }

    const userToStore: StoredUser = {
      role: userResponse.role,
      email: userResponse.email,
      name: userResponse.name,
      userId: userResponse.userId
    };

    this._isLoggedIn$.next(true);
    this._role$.next(userToStore.role);
    this._currentUser$.next(userToStore);

    if (typeof window !== 'undefined' && localStorage) {
      localStorage.setItem(this.USER_STORAGE_KEY, JSON.stringify(userToStore));
    }
  }

  logout(): void {
    if (typeof window !== 'undefined' && localStorage) {
      localStorage.removeItem(this.USER_STORAGE_KEY);
      localStorage.removeItem("data");
      localStorage.removeItem("requests");
      localStorage.removeItem("user");
    }
    this._isLoggedIn$.next(false);
    this._role$.next(null);
    this._currentUser$.next(null);
  }

  public getCurrentUserEmail(): string | null {
    const currentUser = this._currentUser$.value;
    return currentUser ? currentUser.email : null;
  }

  public getCurrentUserValue(): StoredUser | null {
    return this._currentUser$.value;
  }
}
