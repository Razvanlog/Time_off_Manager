import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TimeOffRequestApiService } from '../../services/time-off-request-api.service';
import { AuthService } from '../../services/auth.service';
import { TimeOffRequestResponsePayload } from '../../core/models/time-off-request.models';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss'],
})
export class HistoryComponent implements OnInit {
  requests: TimeOffRequestResponsePayload[] = [];
  currentUserEmail: string | null = null;
  isLoading = false;
  errorMessage: string | null = null;

  constructor(
    private timeOffRequestApi: TimeOffRequestApiService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUserEmail = this.authService.getCurrentUserEmail();
    if (this.currentUserEmail) {
      this.loadRequestHistory(this.currentUserEmail);
    } else {
      console.error('HistoryComponent: User not logged in.');
      this.errorMessage = 'You need to be logged in to view your request history.';
    }
  }

  loadRequestHistory(email: string): void {
    this.isLoading = true;
    this.errorMessage = null;
    this.requests = [];

    this.timeOffRequestApi.getRequestsForUser(email).subscribe({
      next: (data) => {
        this.requests = data.sort((a, b) => new Date(b.start).getTime() - new Date(a.start).getTime());
        this.isLoading = false;
        if (this.requests.length === 0) {
          this.errorMessage = 'You have no time-off request history.';
        }
      },
      error: (err) => {
        console.error('HistoryComponent: Error fetching request history:', err);
        this.errorMessage = 'Failed to load your request history. Please try again later.';
        this.isLoading = false;
      }
    });
  }

  mapLeaveTypeNumberToString(typeValue: number | undefined): string {
    if (typeValue === undefined || typeValue === null) {
      return 'N/A';
    }
    switch (typeValue) {
      case 0: return 'VACATION';
      case 1: return 'SICK';
      case 2: return 'PERSONAL';
      default: return `Unknown Type (${typeValue})`;
    }
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

  mapStatusToString(statusValue: number | undefined): string {
    if (statusValue === undefined || statusValue === null) {
      return 'N/A';
    }
    switch (statusValue) {
      case 0: return 'Pending';
      case 1: return 'Approved';
      case 2: return 'Rejected';
      default: return `Unknown (${statusValue})`;
    }
  }
}
