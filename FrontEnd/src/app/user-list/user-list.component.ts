import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserApiService, UserResponse } from '../../app/services/user-api.service';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: UserResponse[] = [];

  constructor(private userApiService: UserApiService) {}

  ngOnInit(): void {
    this.userApiService.getUsers().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Failed to load users:', err)
    });
  }
}
