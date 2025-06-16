import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ManagerPartComponent } from './layout/manager-part/manager-part.component';
import { HomeComponent } from './layout/home/home.component';
import { TeamComponent } from './layout/team/team.component';
import { SubmitRequestComponent } from './layout/submit-request/submit-request.component';
import { EditRequestsComponent } from './layout/edit-requests/edit-requests.component';
import { HistoryComponent } from './layout/history/history.component';
import { LoginComponent } from './layout/login/login.component';
import { SignupComponent } from './layout/signup/signup.component';
import { AdminComponent } from './components/admin-dashboard/admin.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'submit-request', component: SubmitRequestComponent },
  { path: 'edit-requests', component: EditRequestsComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'review', component: ManagerPartComponent },
  { path: 'team', component: TeamComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'admin', component: AdminComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
