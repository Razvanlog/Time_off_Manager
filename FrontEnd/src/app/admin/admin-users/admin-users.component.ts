import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';
import { AdminUserService, AdminUpdateUserRequestPayload } from '../admin-user.service';
import {UserService} from '../../services/user.service';
import {UserRole} from '../../enums/user-role.enum';
import {TimeOffRequest} from '../../services/requests-api.service';
import {LeavePolicy} from '../../models/leave-policy';
import {TimeOffRequestService} from '../../services/time-off-request.service';
import {LeavePolicyService} from '../../services/leave-policy.service';

@Component({
  selector: 'app-admin-users',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.scss'],
})
export class AdminUsersComponent implements OnInit {
  users: User[] = [];
  allRequests: TimeOffRequest[] = [];
  leavePolicies: LeavePolicy[] = [];

  showCreateForm: boolean = false;
  editingUser: User | null = null;
  UserRole = UserRole;
  userRoles = Object.values(UserRole);
  newUser: Partial<User> = {};

  constructor(
    private userService: UserService,
    private requestService: TimeOffRequestService,
    private policyService: LeavePolicyService
  ) {
    this.loadUsers();
    this.resetNewUserForm();
    this.loadAllRequests();
    this.loadLeavePolicies();
  }

  ngOnInit(): void {
    this.loadUsers();
    this.resetNewUserForm();
    this.loadAllRequests();
    this.loadLeavePolicies();
  }

  loadLeavePolicies(): void {
    // this.policyService.getAllPolicies().subscribe(data => { this.leavePolicies = data; });
  }

  updatePolicy(policy: LeavePolicy): void {
    // this.policyService.updatePolicy(policy).subscribe(updatedPolicy => {
    //   const index = this.leavePolicies.findIndex(p => p.id === updatedPolicy.id);
    //   if (index !== -1) { this.leavePolicies[index] = updatedPolicy; }
    //   alert('Policy updated successfully!');
    // });
  }

  loadAllRequests(): void {
    this.requestService.getAllRequests().subscribe(data => { this.allRequests = data;
      console.log(this.allRequests);
      // localStorage.setItem("requests",JSON.stringify(data));
    });
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(data => { this.users = data; });
  }

  resetNewUserForm(): void {
    this.newUser = { name: '', email: '', password: '', role: UserRole.EMPLOYEE };
  }

  createUser(): void {
    this.userService.createUser(this.newUser).subscribe(() => { this.loadUsers(); this.showCreateForm = false; this.resetNewUserForm(); });
  }

  startEdit(user: User): void {
    this.editingUser = { ...user }; this.showCreateForm = false;
  }

  cancelEdit(): void {
    this.editingUser = null;
  }

  updateUser(): void {
    if (this.editingUser && this.editingUser.email) {
      this.userService.updateUser(this.editingUser.email, this.editingUser).subscribe(() => { this.loadUsers(); this.editingUser = null; });
    }
  }

  deleteUser(userId: String | undefined): void {
    if (userId === undefined) {
      alert('Error: User ID is missing.');
      return;
    }
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser(userId).subscribe(() => {
        this.loadUsers();
      });
    }
  }
  //
  // onRoleChange(user: User,newRoleString: UserRole): void {
  //   console.log(`Role change triggered for user ${user.email} to ${newRoleString}`);
  //   const updatePayload: User = user
  //   updatePayload.role=newRoleString;
  //   // updatePayload.name=newName;
  //   //   {
  //   //   password: user.password,
  //   //   role: newRoleString.toUpperCase(),
  //   //   name: user.name,
  //   //   email: user.email
  //   // };
  //
  //   this.isLoading = true;
  //   this.adminUserService.updateUser(user.email, updatePayload).subscribe({
  //     next: (updatedUser) => {
  //         this.isLoading=false;
  //         console.log('User role updated successfully via backend:', updatedUser);
  //         this.loadUsers();
  //     //   const index = this.users.findIndex(u => u.email === updatedUser.userId);
  //     //   if (index !== -1) {
  //     //     this.users[index] = updatedUser;
  //     //   }
  //     //   this.isLoading = false;
  //     },
  //     error: (err) => {
  //       this.errorMessage = `Failed to update role for ${user.name}. ` + (err.message || 'Server error');
  //       this.isLoading = false;
  //     //   console.error('Error updating user role:', err);
  //       this.loadUsers();
  //     }
  //   });
  // }
  //
  // editUser(user: User): void {
  //   console.log('Edit user clicked (functionality for full edit form not yet implemented):', user);
  //   alert(`Placeholder: Editing user ${user.name} (ID: ${user.userId}). Full edit form to be implemented.`);
  // }
  //
  // deleteUser(userId: number): void {
  //   console.log('Delete user clicked (functionality not yet implemented):', userId);
  //   alert(`Placeholder: Deleting user ID ${userId}. Functionality to be implemented.`);
  // }
}
