import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminLeavePoliciesComponent } from './admin-leave-policies/admin-leave-policies.component';
import { AdminRequestsComponent } from './admin-requests/admin-requests.component';

const routes: Routes = [
  { path: 'users', component: AdminUsersComponent },
  { path: 'leave-policies', component: AdminLeavePoliciesComponent },
  { path: 'requests', component: AdminRequestsComponent },
  { path: '', redirectTo: 'users', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
