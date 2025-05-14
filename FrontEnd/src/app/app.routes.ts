import {NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';

//WEBSITE COMPONENTS
import { ManagerPartComponent } from './layout/manager-part/manager-part.component'
import {HomeComponent} from './layout/home/home.component';
import {TeamComponent} from './layout/team/team.component';
import {UserListComponent} from './user-list/user-list.component';
import {UserService} from './User/user.service';
import {SubmitRequestComponent} from './layout/submit-request/submit-request.component';
import {EditRequestsComponent} from './layout/edit-requests/edit-requests.component';
import {HistoryComponent} from './layout/history/history.component';

export const routes: Routes = [
  {
    path: "", redirectTo: "home", pathMatch: "full"
  },
  {
    path: "home",
    component: HomeComponent
    // loadComponent: () => import('./layout/home/home.component').then(c => c.HomeComponent)
  },
  {
    path: "submit-request",
    component: SubmitRequestComponent
    // loadComponent: () => import('./layout/submit-request/submit-request.component').then(c => c.SubmitRequestComponent)
  },
  {
    path: "edit-requests",
    component: EditRequestsComponent
    // loadComponent: () => import('./layout/edit-requests/edit-requests.component').then(c => c.EditRequestsComponent)
  },
  {
    path: "history",
    component: HistoryComponent
    // loadComponent: () => import('./layout/history/history.component').then(c => c.HistoryComponent)
  },
  {
    path: "review",
    component: ManagerPartComponent
    // loadComponent: () => import('./layout/manager-part/manager-part.component').then(c => c.ManagerPartComponent)
  },
  {
    path: "team",
    component: TeamComponent
    // loadComponent: () => import('./layout/team/team.component').then(c => c.TeamComponent)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), BrowserModule],
  exports: [RouterModule, CommonModule],
  providers: [UserService]
})
export class AppRoutingModule{}
