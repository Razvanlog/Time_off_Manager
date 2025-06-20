// Path: FrontEnd/src/app/services/leave-policy.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LeavePolicy } from '../models/leave-policy';

@Injectable({
  providedIn: 'root'
})
export class LeavePolicyService {
  private apiUrl = 'http://localhost:8080/policies';

  constructor(private http: HttpClient) { }

  getAllPolicies(): Observable<LeavePolicy[]> {
    return this.http.get<LeavePolicy[]>(this.apiUrl);
  }

  updatePolicy(policy: LeavePolicy): Observable<LeavePolicy> {
    return this.http.put<LeavePolicy>(`${this.apiUrl}/${policy.id}`, policy);
  }
}
