import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {UserResponse} from './user-api.service';
// import {TimeOffRequest} from './mock-request.service';
import {Time} from '@angular/common';
import {managerInj} from '../layout/manager-part/manager-part.component';

// export interface CreateTimeOffRequest {
//   email: string;
//   password: string;
//   start: Date;
//   end: Date;
//   leaveType: string;
//   description: string;
//
// }

export interface TimeOffRequest {
    user: string;
    password: string;
    start: Date;
    end: Date;
    type: BigInt;
    status: number;
    requestUserNumber: BigInt;
    description: string;
    currentStatus: string;
    selectedStatus: string;
}



@Injectable({
  providedIn: 'root'
})
export class RequestsApiService {
  private baseUrl = 'http://localhost:8080/TimeOffRequestsController';

  constructor(private http: HttpClient) {}


  get(data: managerInj): Observable<Array<TimeOffRequest>>{
    console.log(JSON.stringify(data));
    console.log(`${data.email}`);
    console.log(data.name);
    return this.http.get<Array<TimeOffRequest>>(`${this.baseUrl}/all/${data.email}`);
  }
  acceptRequest(request: TimeOffRequest): Observable<TimeOffRequest>{
    console.log(`accepting request ${request.user} ${request.requestUserNumber}`)
    return this.http.post<TimeOffRequest>(`${this.baseUrl}/accept/${request.user}/${request.requestUserNumber}`,null);
  }
  rejectRequest(request: TimeOffRequest): Observable<TimeOffRequest>{
    console.log(`accepting request ${request.user} ${request.requestUserNumber}`)
    return this.http.post<TimeOffRequest>(`${this.baseUrl}/reject/${request.user}/${request.requestUserNumber}`,null);
  }// signup(data: CreateProjectRequest): Observable<ProjectResponse> {
  //   return this.http.post<ProjectResponse>(`${this.baseUrl}/signup`, data);
  // }
  //
  // login(data: CreateProjectRequest): Observable<ProjectResponse> {
  //   return this.http.post<ProjectResponse>(`${this.baseUrl}/login`, data);
  // }
}
