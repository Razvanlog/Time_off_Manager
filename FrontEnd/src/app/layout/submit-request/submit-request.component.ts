import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidatorFn } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TimeOffRequestApiService } from '../../services/time-off-request-api.service';
import { CreateTimeOffRequestPayload } from '../../core/models/time-off-request.models';
import { AuthService } from '../../services/auth.service';

export function notInPastValidator(): ValidatorFn {
  return (control: AbstractControl): {[key: string]: any} | null => {
    if (!control.value) {
      return null;
    }
    const selectedDate = new Date(control.value);
    const today = new Date();

    selectedDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    if (selectedDate < today) {
      return { 'dateInPast': { value: control.value } };
    }
    return null;
  };
}

export function endDateValidator(startDateControlName: string): ValidatorFn {
  return (endDateControl: AbstractControl): {[key: string]: any} | null => {
    if (!endDateControl.parent || !endDateControl.value) {
      return null;
    }
    const startDateControl = endDateControl.parent.get(startDateControlName);
    if (!startDateControl || !startDateControl.value) {
      return null;
    }

    const startDate = new Date(startDateControl.value);
    const endDate = new Date(endDateControl.value);

    if (isNaN(startDate.getTime()) || isNaN(endDate.getTime())) {
      return null;
    }

    startDate.setHours(0,0,0,0);
    endDate.setHours(0,0,0,0);

    if (endDate < startDate) {
      return { 'endDateBeforeStartDate': { value: endDateControl.value } };
    }
    return null;
  };
}

@Component({
  selector: 'app-submit-request',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './submit-request.component.html',
  standalone: true,
  styleUrls: ['./submit-request.component.scss']
})
export class SubmitRequestComponent implements OnInit {
  requestForm: FormGroup;
  currentUserEmail: string | null = null;
  minDate: string;

  constructor(
    private fb: FormBuilder,
    private timeOffRequestApi: TimeOffRequestApiService,
    private authService: AuthService,
    private router: Router
  ) {
    const today = new Date();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    this.minDate = `${today.getFullYear()}-${month}-${day}`;

    this.requestForm = this.fb.group({
      startDate: ['', [Validators.required, notInPastValidator()]],
      endDate: ['', [Validators.required, endDateValidator('startDate')]],
      leaveType: ['', Validators.required],
      notes: ['']
    });

    const startDateControl = this.requestForm.get('startDate');
    if (startDateControl) {
      startDateControl.valueChanges.subscribe(() => {
        if(startDateControl.valid) {
          this.requestForm.get('endDate')?.updateValueAndValidity({ emitEvent: false });
        } else {
          this.requestForm.get('endDate')?.updateValueAndValidity({ emitEvent: false });
        }
      });
    }
  }

  ngOnInit(): void {
    this.currentUserEmail = this.authService.getCurrentUserEmail();

    if (!this.currentUserEmail) {
      console.error('SubmitRequestComponent: User email not found. User might not be logged in.');
      alert('You must be logged in to submit a request. Redirecting to login.');
      this.router.navigate(['/login']);
    } else {
      console.log('SubmitRequestComponent: Current user email:', this.currentUserEmail);
    }
  }

  calculateRequestedDays(startDateStr: string, endDateStr: string): number {
    if (!startDateStr || !endDateStr) {
      console.warn("calculateRequestedDays: Start or end date string is missing.");
      return 0;
    }

    const start = new Date(startDateStr);
    const end = new Date(endDateStr);

    const startDateUtc = new Date(Date.UTC(start.getUTCFullYear(), start.getUTCMonth(), start.getUTCDate()));
    const endDateUtc = new Date(Date.UTC(end.getUTCFullYear(), end.getUTCMonth(), end.getUTCDate()));

    if (isNaN(startDateUtc.getTime()) || isNaN(endDateUtc.getTime())) {
      console.error("calculateRequestedDays: Invalid date string provided.");
      return -2;
    }

    if (endDateUtc < startDateUtc) {
      console.warn("calculateRequestedDays: End date is before start date.");
      return -1;
    }

    const differenceInTime = endDateUtc.getTime() - startDateUtc.getTime();
    const differenceInDays = differenceInTime / (1000 * 3600 * 24);

    return Math.round(differenceInDays) + 1;
  }

  onSubmit() {
    this.requestForm.markAllAsTouched();

    if (!this.requestForm.valid) {
      console.warn('SubmitRequestComponent: Form is invalid based on validators.');
      if (this.requestForm.get('startDate')?.hasError('required')) {
        alert('Start date is required.');
      } else if (this.requestForm.get('startDate')?.hasError('dateInPast')) {
        alert('Start date cannot be in the past. Please select a current or future date.');
      } else if (this.requestForm.get('endDate')?.hasError('required')) {
        alert('End date is required.');
      } else if (this.requestForm.get('endDate')?.hasError('endDateBeforeStartDate')) {
        alert('End date cannot be before the start date.');
      } else if (this.requestForm.get('leaveType')?.hasError('required')) {
        alert('Leave type is required.');
      }
      else {
        alert('Please correct the errors in the form.');
      }
      return;
    }

    if (!this.currentUserEmail) {
      console.error('SubmitRequestComponent: Critical error - currentUserEmail is null despite ngOnInit check.');
      alert('Error: User session not found. Please log in again.');
      this.router.navigate(['/login']);
      return;
    }

    const formValue = this.requestForm.value;
    const requestedDays = this.calculateRequestedDays(formValue.startDate, formValue.endDate);

    if (requestedDays <= 0) {
      if (requestedDays === -1) {
        alert('Error in date range: End date must be on or after the start date.');
      } else if (requestedDays === -2) {
        alert('Invalid start or end date format provided to calculation.');
      } else {
        alert('The requested number of days must be at least 1. Please verify dates.');
      }
      return;
    }

    const payload: CreateTimeOffRequestPayload = {
      userEmail: this.currentUserEmail,
      start: formValue.startDate,
      end: formValue.endDate,
      leaveType: formValue.leaveType,
      description: formValue.notes?.trim() || null,
      requestedDays: requestedDays
    };

    console.log('SubmitRequestComponent: Submitting payload:', payload);

    this.timeOffRequestApi.submitRequest(payload).subscribe({
      next: (response) => {
        console.log('SubmitRequestComponent: Request Submitted Successfully:', response);
        alert('Time-off request submitted successfully!');
        this.requestForm.reset();
      },
      error: (errorResponse) => {
        console.error('SubmitRequestComponent: Error Submitting Request:', errorResponse);
        let errorMessage = 'Failed to submit time-off request.';
        if (errorResponse.error) {
          if (typeof errorResponse.error === 'string') {
            try {
              const parsedError = JSON.parse(errorResponse.error);
              errorMessage += `\nServer: ${parsedError.message || errorResponse.error}`;
            } catch (e) {
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
      }
    });
  }
}
