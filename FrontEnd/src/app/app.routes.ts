import {NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';

import { ManagerPartComponent } from './layout/manager-part/manager-part.component';
import {HomeComponent} from './layout/home/home.component';
import {TeamComponent} from './layout/team/team.component';
import {UserService} from './User/user.service';
import {SubmitRequestComponent} from './layout/submit-request/submit-request.component';
import {EditRequestsComponent} from './layout/edit-requests/edit-requests.component';
import {HistoryComponent} from './layout/history/history.component';
import { LoginComponent } from './layout/login/login.component';
import { SignupComponent } from './layout/signup/signup.component';

export const routes: Routes = [

  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "submit-request",
    component: SubmitRequestComponent
  },
  {
    path: "edit-requests",
    component: EditRequestsComponent
  },
  {
    path: "history",
    component: HistoryComponent
  },
  {
    path: "review",
    component: ManagerPartComponent
  },
  {
    path: "team",
    component: TeamComponent
  },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },

  {
    path: 'admin',
    loadChildren: () => import('./admin/admin-module').then(m => m.AdminModule)
  },


  { path: '', redirectTo: 'login', pathMatch: 'full' } // Default redirect
];


@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule],
  exports: [RouterModule, CommonModule],
  providers: [UserService]
})
export class AppRoutingModule{}
