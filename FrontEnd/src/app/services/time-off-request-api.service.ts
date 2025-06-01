import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateTimeOffRequestPayload, TimeOffRequestResponsePayload } from '../core/models/time-off-request.models';

@Injectable({
  providedIn: 'root'
})
export class TimeOffRequestApiService {
  private baseUrl = 'http://localhost:8080/TimeOffRequestsController';

  constructor(private http: HttpClient) {}

  private getHttpOptions(isGetRequest: boolean = false) {
    let headers = new HttpHeaders();
    if (!isGetRequest) {
      headers = headers.set('Content-Type', 'application/json');
    }
    return { headers };
  }

  submitRequest(data: CreateTimeOffRequestPayload): Observable<TimeOffRequestResponsePayload> {
    return this.http.post<TimeOffRequestResponsePayload>(this.baseUrl, data, this.getHttpOptions());
  }

  getRequestsForUser(email: string): Observable<TimeOffRequestResponsePayload[]> {
    return this.http.get<TimeOffRequestResponsePayload[]>(`${this.baseUrl}/${email}`, this.getHttpOptions(true));
  }

  updateRequest(
    userMail: string,
    requestNumber: number,
    payload: CreateTimeOffRequestPayload
  ): Observable<TimeOffRequestResponsePayload> {
    return this.http.put<TimeOffRequestResponsePayload>(
      `${this.baseUrl}/${userMail}/${requestNumber}`,
      payload,
      this.getHttpOptions()
    );
  }

  deleteRequest(
    userMail: string,
    requestNumber: number
  ): Observable<TimeOffRequestResponsePayload> {
    return this.http.delete<TimeOffRequestResponsePayload>(
      `${this.baseUrl}/${userMail}/${requestNumber}`,
      this.getHttpOptions(true)
    );
  }
}
