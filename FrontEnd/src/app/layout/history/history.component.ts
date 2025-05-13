import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MockRequestService, TimeOffRequest } from '../../services/mock-request.service';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss'],
})
export class HistoryComponent {
  requests: TimeOffRequest[] = [];

  // constructor(private requestService: MockRequestService) {
  //   this.requests = this.requestService.getRequests();
  // }
}
