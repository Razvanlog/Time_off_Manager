import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MockRequestService, TimeOffRequest } from '../../services/mock-request.service';

@Component({
  selector: 'app-edit-requests',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-requests.component.html',
  styleUrls: ['./edit-requests.component.scss'],
})
export class EditRequestsComponent {
  requests: TimeOffRequest[] = [];
  editingId: number | null = null;
  editedRequest: TimeOffRequest | null = null;

  constructor(private requestService: MockRequestService) {
    this.requests = this.requestService.getRequests();
  }

  startEditing(request: TimeOffRequest) {
    this.editingId = request.id;
    this.editedRequest = { ...request };
  }

  cancelEditing() {
    this.editingId = null;
    this.editedRequest = null;
  }

  saveChanges() {
    if (this.editedRequest) {
      this.requestService.updateRequest(this.editedRequest);
      this.editingId = null;
      this.editedRequest = null;
    }
  }
}
