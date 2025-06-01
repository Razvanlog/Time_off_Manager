import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminUserService, User, AdminUpdateUserRequestPayload } from '../admin-user.service';

@Component({
  selector: 'app-admin-users',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.scss'],
})
export class AdminUsersComponent implements OnInit {
  users: User[] = [];
  roles = ['EMPLOYEE', 'MANAGER', 'ADMIN'];
  isLoading = false;
  errorMessage = '';

  constructor(private adminUserService: AdminUserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.adminUserService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.isLoading = false;
        console.log('Users loaded from backend:', this.users);
      },
      error: (err) => {
        this.errorMessage = 'Failed to load users. ' + (err.message || 'Server error');
        this.isLoading = false;
        console.error('Error loading users:', err);
      }
    });
  }

  onRoleChange(user: User, newRoleString: string): void {
    console.log(`Role change triggered for user ${user.userId} to ${newRoleString}`);
    const updatePayload: AdminUpdateUserRequestPayload = {
      name: user.name,
      email: user.email,
      role: newRoleString.toUpperCase()
    };

    this.isLoading = true;
    this.adminUserService.updateUser(user.userId, updatePayload).subscribe({
      next: (updatedUser) => {
        console.log('User role updated successfully via backend:', updatedUser);
        const index = this.users.findIndex(u => u.userId === updatedUser.userId);
        if (index !== -1) {
          this.users[index] = updatedUser;
        }
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = `Failed to update role for ${user.name}. ` + (err.message || 'Server error');
        this.isLoading = false;
        console.error('Error updating user role:', err);
        this.loadUsers();
      }
    });
  }

  editUser(user: User): void {
    console.log('Edit user clicked (functionality for full edit form not yet implemented):', user);
    alert(`Placeholder: Editing user ${user.name} (ID: ${user.userId}). Full edit form to be implemented.`);
  }

  deleteUser(userId: number): void {
    console.log('Delete user clicked (functionality not yet implemented):', userId);
    alert(`Placeholder: Deleting user ID ${userId}. Functionality to be implemented.`);
  }
}
