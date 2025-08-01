import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CreateUserRequest {
  name: string;
  email: string;
  password: string;
  role: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface UserResponse {
  userId?: number;
  name?: string;
  email: string;
  role: string;
}

@Injectable({ providedIn: 'root' })
export class UserApiService {
  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  signup(data: CreateUserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/signup`, data);
  }

  login(data: LoginRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/login`, data);
  }
  getUsers(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${this.baseUrl}`);
  }
}
