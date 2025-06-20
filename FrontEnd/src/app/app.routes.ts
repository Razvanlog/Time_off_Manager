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

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'submit-request', component: SubmitRequestComponent },
  { path: 'edit-requests', component: EditRequestsComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'manager-dashboard', component: ManagerPartComponent },
  { path: 'team', component: TeamComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule],
  exports: [RouterModule, CommonModule],
  providers: [UserService]
})
export class AppRoutingModule{}
