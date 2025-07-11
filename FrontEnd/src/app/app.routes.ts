import {NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';


import { ManagerPartComponent } from './layout/manager-part/manager-part.component'
import {HomeComponent} from './layout/home/home.component';
import {TeamComponent} from './layout/team/team.component';
import {UserListComponent} from './user-list/user-list.component';
import {UserService} from './User/user.service';
import {SubmitRequestComponent} from './layout/submit-request/submit-request.component';
import {EditRequestsComponent} from './layout/edit-requests/edit-requests.component';
import {HistoryComponent} from './layout/history/history.component';
import { LoginComponent } from './layout/login/login.component';
import { SignupComponent } from './layout/signup/signup.component';
import {AdminDashboardComponent} from './components/admin-dashboard/admin.component';
import {AdminUsersComponent} from './admin/admin-users/admin-users.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'submit-request', component: SubmitRequestComponent },
  { path: 'edit-requests', component: EditRequestsComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'review-requests', component: ManagerPartComponent },
  { path: 'team', component: TeamComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {path:'admin-dashboard', component: AdminDashboardComponent},
  {path:'admin/users', component: AdminUsersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule],
  exports: [RouterModule, CommonModule],
  providers: [UserService]
})
export class AppRoutingModule{}
