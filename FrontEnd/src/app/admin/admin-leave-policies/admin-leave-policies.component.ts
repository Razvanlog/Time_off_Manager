import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-leave-policies',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-leave-policies.component.html',
  styleUrls: ['./admin-leave-policies.component.scss'],
})
export class AdminLeavePoliciesComponent {
  policies = [
    { type: 'Vacation', limit: 20 },
    { type: 'Sick', limit: 10 },
    { type: 'Personal', limit: 5 },
  ];
}
