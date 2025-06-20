import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


export interface User {
  userId: number;
  name: string;
  email: string;
  role: string;
}

export interface AdminUpdateUserRequestPayload {
  name: string;
  email: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AdminUserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`)
      .pipe(catchError(this.handleError));
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/id/${userId}`)
      .pipe(catchError(this.handleError));
  }

  updateUser(userId: number, userData: AdminUpdateUserRequestPayload): Observable<User> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.put<User>(`${this.apiUrl}/id/${userId}`, userData, httpOptions)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: any) {
    console.error('API Error in AdminUserService:', error);

    return throwError(() => new Error('An API error occurred. Please check the console for details and try again later.'));
  }
}
