import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {TimeOffRequest} from './requests-api.service';

@Injectable({
  providedIn: 'root'
})
export class TimeOffRequestService {
  private apiUrl = 'http://localhost:8080/TimeOffRequestsController';

  constructor(private http: HttpClient) { }

  getAllRequests(): Observable<TimeOffRequest[]> {
    return this.http.get<TimeOffRequest[]>(this.apiUrl);
  }
}
