import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule} from './app.routes';
import {FormsModule} from '@angular/forms';
import {UserListComponent} from './user-list/user-list.component';
import {HeaderComponent} from './core/header/header.component';
// import {App}
import { AppComponent } from './app.component';
import { provideHttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import {UserService} from './User/user.service';
import {RouterOutlet} from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterOutlet
  ],
  providers: [UserService, provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
