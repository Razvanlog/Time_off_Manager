import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { UserRole } from '../../enums/user-role.enum';
import { TimeOffRequest } from '../../models/time-off-request';
import { TimeOffRequestService } from '../../services/time-off-request.service';
import { LeavePolicy } from '../../models/leave-policy';
import { LeavePolicyService } from '../../services/leave-policy.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminDashboardComponent implements OnInit {
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
    this.newUser = { fullName: '', email: '', password: '', role: UserRole.EMPLOYEE };
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
    if (this.editingUser && this.editingUser.id) {
      this.userService.updateUser(this.editingUser.id, this.editingUser).subscribe(() => { this.loadUsers(); this.editingUser = null; });
    }
  }

  deleteUser(userId: number | undefined): void {
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
}
