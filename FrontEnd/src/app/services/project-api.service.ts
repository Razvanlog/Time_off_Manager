import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserResponse} from './user-api.service';

export interface CreateProjectRequest {
  name: string;
  email: string;
  password: string;
}

export interface ProjectResponse {
  name: string;
  manager: UserResponse;
  employees: UserResponse[];
}

@Injectable({
  providedIn: 'root'
})
export class ProjectApiService {
  private baseUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  signup(data: CreateProjectRequest): Observable<ProjectResponse> {
    return this.http.post<ProjectResponse>(`${this.baseUrl}/signup`, data);
  }

  login(data: CreateProjectRequest): Observable<ProjectResponse> {
    return this.http.post<ProjectResponse>(`${this.baseUrl}/login`, data);
  }
}
