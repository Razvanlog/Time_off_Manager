import { Component, OnInit } from '@angular/core';
import { CommonModule, formatDate } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { TimeOffRequestApiService } from '../../services/time-off-request-api.service';
import { AuthService } from '../../services/auth.service';
import { TimeOffRequestResponsePayload, CreateTimeOffRequestPayload } from '../../core/models/time-off-request.models';

@Component({
  selector: 'app-edit-requests',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-requests.component.html',
  styleUrls: ['./edit-requests.component.scss'],
})
export class EditRequestsComponent implements OnInit {
  requests: TimeOffRequestResponsePayload[] = [];
  editingId: number | null = null;

  editedRequest: {
    id?: number;
    requestUserNumber: number;
    user: string;
    status: number;
    startDate: string;
    endDate: string;
    leaveType: string;
    description: string | null;
    requestedDays: number;
  } | null = null;

  currentUserEmail: string | null = null;
  isLoading = false;
  errorMessage: string | null = null;
  minDate: string;

  constructor(
    private timeOffRequestApi: TimeOffRequestApiService,
    private authService: AuthService,
    private router: Router
  ) {
    const today = new Date();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    this.minDate = `${today.getFullYear()}-${month}-${day}`;
  }

  ngOnInit(): void {
    this.currentUserEmail = this.authService.getCurrentUserEmail();
    if (this.currentUserEmail) {
      this.loadRequests(this.currentUserEmail);
    } else {
      this.errorMessage = 'You need to be logged in to view and edit requests.';
    }
  }

  loadRequests(email: string): void {
    this.isLoading = true;
    this.errorMessage = null;
    this.requests = [];

    this.timeOffRequestApi.getRequestsForUser(email).subscribe({
      next: (data) => {
        this.requests = data;
        this.isLoading = false;
        if (this.requests.length === 0) {
          this.errorMessage = 'You have no time-off requests.';
        }
      },
      error: (err) => {
        this.errorMessage = 'Failed to load your time-off requests. Please try again later.';
        this.isLoading = false;
      }
    });
  }

  calculateRequestedDays(startDateStr: string, endDateStr: string): number {
    if (!startDateStr || !endDateStr) return 0;
    const start = new Date(startDateStr);
    const end = new Date(endDateStr);
    const startDateUtc = new Date(Date.UTC(start.getUTCFullYear(), start.getUTCMonth(), start.getUTCDate()));
    const endDateUtc = new Date(Date.UTC(end.getUTCFullYear(), end.getUTCMonth(), end.getUTCDate()));
    if (isNaN(startDateUtc.getTime()) || isNaN(endDateUtc.getTime())) return 0;
    if (endDateUtc < startDateUtc) return 0;
    const differenceInTime = endDateUtc.getTime() - startDateUtc.getTime();
    const differenceInDays = differenceInTime / (1000 * 3600 * 24);
    return Math.round(differenceInDays) + 1;
  }

  mapLeaveTypeNumberToString(typeValue: number | undefined): string {
    if (typeValue === undefined || typeValue === null) return 'N/A';
    switch (typeValue) {
      case 0: return 'VACATION';
      case 1: return 'SICK';
      case 2: return 'PERSONAL';
      default: return `Unknown (${typeValue})`;
    }
  }

  startEditing(request: TimeOffRequestResponsePayload): void {
    if (request.requestUserNumber === undefined || request.requestUserNumber === null) {
      alert("Error: Cannot edit this request due to missing identifier.");
      return;
    }
    if (request.type === undefined || request.type === null) {
      alert("Error: Request data is incomplete (missing leave type).");
      return;
    }

    this.editingId = request.requestUserNumber;

    this.editedRequest = {
      id: request.id,
      requestUserNumber: request.requestUserNumber,
      user: request.user,
      status: request.status,
      startDate: request.start ? formatDate(new Date(request.start), 'yyyy-MM-dd', 'en-US') : '',
      endDate: request.end ? formatDate(new Date(request.end), 'yyyy-MM-dd', 'en-US') : '',
      leaveType: this.mapLeaveTypeNumberToString(request.type),
      description: request.description,
      requestedDays: this.calculateRequestedDays(request.start, request.end)
    };
  }

  cancelEditing(): void {
    this.editingId = null;
    this.editedRequest = null;
  }

  saveChanges(): void {
    if (!this.editedRequest || !this.currentUserEmail || this.editingId === null) {
      alert("Error: Cannot save changes. Please try again.");
      return;
    }

    if (!this.editedRequest.startDate || !this.editedRequest.endDate || !this.editedRequest.leaveType || this.editedRequest.leaveType === 'N/A' || this.editedRequest.leaveType === '') {
      alert("Start date, end date, and a valid leave type are required for saving.");
      return;
    }

    const calculatedDays = this.calculateRequestedDays(this.editedRequest.startDate, this.editedRequest.endDate);
    if (calculatedDays <= 0) {
      alert("The selected date range must result in at least 1 requested day.");
      return;
    }

    const updatePayload: CreateTimeOffRequestPayload = {
      userEmail: this.currentUserEmail,
      start: this.editedRequest.startDate,
      end: this.editedRequest.endDate,
      leaveType: this.editedRequest.leaveType,
      description: this.editedRequest.description?.trim() || null,
      requestedDays: calculatedDays
    };

    this.isLoading = true;
    this.timeOffRequestApi.updateRequest(
      this.currentUserEmail,
      this.editingId,
      updatePayload
    ).subscribe({
      next: () => {
        alert('Time-off request updated successfully!');
        this.isLoading = false;
        this.cancelEditing();
        if (this.currentUserEmail) this.loadRequests(this.currentUserEmail);
      },
      error: (errorResponse) => {
        let errorMessage = 'Failed to update time-off request.';
        if (errorResponse.error) {
          if (typeof errorResponse.error === 'string') {
            try {
              const parsedError = JSON.parse(errorResponse.error);
              errorMessage += `\nServer: ${parsedError.message || errorResponse.error}`;
            } catch {
              errorMessage += `\nServer: ${errorResponse.error}`;
            }
          } else if (errorResponse.error.message) {
            errorMessage += `\nServer: ${errorResponse.error.message}`;
          } else if (errorResponse.error.error) {
            errorMessage += `\nServer: ${errorResponse.error.error}`;
          }
        } else if (errorResponse.message) {
          errorMessage += `\nDetails: ${errorResponse.message}`;
        }
        alert(errorMessage);
        this.isLoading = false;
      }
    });
  }

  deleteRequest(requestToDelete: TimeOffRequestResponsePayload): void {
    if (!this.currentUserEmail) {
      alert('Error: User not identified. Please log in again.');
      return;
    }

    if (requestToDelete.requestUserNumber === undefined || requestToDelete.requestUserNumber === null) {
      alert('Error: Could not identify the specific request to delete.');
      return;
    }

    const startDateFormatted = requestToDelete.start ? formatDate(new Date(requestToDelete.start), 'yyyy-MM-dd', 'en-US') : 'N/A';
    const endDateFormatted = requestToDelete.end ? formatDate(new Date(requestToDelete.end), 'yyyy-MM-dd', 'en-US') : 'N/A';

    if (!confirm(`Are you sure you want to delete the time-off request from ${startDateFormatted} to ${endDateFormatted}?`)) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = null;
    const requestNumberToDelete = requestToDelete.requestUserNumber;

    this.timeOffRequestApi.deleteRequest(this.currentUserEmail, requestNumberToDelete).subscribe({
      next: () => {
        alert('Time-off request deleted successfully!');
        this.isLoading = false;
        if (this.currentUserEmail) {
          this.loadRequests(this.currentUserEmail);
        }
      },
      error: (errorResponse) => {
        let errorMsg = 'Failed to delete time-off request.';
        if (errorResponse.error && errorResponse.error.message) {
          errorMsg += `\nServer: ${errorResponse.error.message}`;
        } else if (errorResponse.error && typeof errorResponse.error === 'string') {
          errorMsg += `\nServer: ${errorResponse.error}`;
        } else if (errorResponse.message) {
          errorMsg += `\nDetails: ${errorResponse.message}`;
        } else {
          errorMsg += `\nAn unknown server error occurred.`;
        }
        alert(errorMsg);
        this.isLoading = false;
      }
    });
  }
}
