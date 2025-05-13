import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface TimeOffRequest {
  id: number;
  startDate: string;
  endDate: string;
  leaveType: string;
  notes?: string;
}

@Injectable({
  providedIn: 'root',
})
export class MockRequestService {
  private requests: TimeOffRequest[] = [
    {
      id: 1,
      startDate: '2024-06-10',
      endDate: '2024-06-14',
      leaveType: 'vacation',
      notes: 'Trip to Beiuș',
    },
    {
      id: 2,
      startDate: '2024-07-01',
      endDate: '2024-07-03',
      leaveType: 'sick',
      notes: '',
    },
  ];

  constructor(private readonly httpClient: HttpClient){

  }
  getRequests(): TimeOffRequest[] {
    return this.requests;
  }

  updateRequest(updated: TimeOffRequest) {
    const index = this.requests.findIndex(r => r.id === updated.id);
    if (index !== -1) {
      this.requests[index] = { ...updated };
    }
  }

  createRequest(requestForm: any){

  }

  // getGender(name: string): Observable<any>{
  //   return this.httpClient.get(`https://api.genderize.io?name=${name}`);
  // }

}
