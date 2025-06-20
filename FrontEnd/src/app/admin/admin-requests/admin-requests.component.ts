import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-requests',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-requests.component.html',
  styleUrls: ['./admin-requests.component.scss'],
})
export class AdminRequestsComponent {
  requests = [
    { employee: 'Alice', startDate: '2024-06-10', endDate: '2024-06-14', type: 'Vacation', status: 'Approved' },
    { employee: 'Bob', startDate: '2024-07-01', endDate: '2024-07-03', type: 'Sick', status: 'Rejected' }
  ];
}
