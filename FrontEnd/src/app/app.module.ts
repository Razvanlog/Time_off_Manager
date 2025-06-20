import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// Your existing imports
import { AppRoutingModule } from './app.routes';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { HeaderComponent } from './core/header/header.component';
import { UserService } from './User/user.service';


import { AdminDashboardComponent } from './components/admin-dashboard/admin.component';

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AppComponent,
    UserListComponent,
    HeaderComponent,
    AdminDashboardComponent
  ],
  providers: [
    UserService
  ],

})
export class AppModule { }
