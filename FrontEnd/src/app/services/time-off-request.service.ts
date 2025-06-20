import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TimeOffRequest } from '../models/time-off-request';

@Injectable({
  providedIn: 'root'
})
export class TimeOffRequestService {
  private apiUrl = 'http://localhost:8080/requests';

  constructor(private http: HttpClient) { }

  getAllRequests(): Observable<TimeOffRequest[]> {
    return this.http.get<TimeOffRequest[]>(this.apiUrl);
  }
}
