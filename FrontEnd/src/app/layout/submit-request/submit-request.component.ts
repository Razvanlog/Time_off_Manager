import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-submit-request',
  // standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './submit-request.component.html',
  styleUrls: ['./submit-request.component.scss']
})
export class SubmitRequestComponent {
  requestForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.requestForm = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      leaveType: ['', Validators.required],
      notes: ['']
    });
  }

  onSubmit() {
    if (this.requestForm.valid) {
      console.log('Request Submitted:', this.requestForm.value);
    }
  }
}
