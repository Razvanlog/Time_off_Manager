import {Component, NgModule, OnInit} from '@angular/core';
import {HelloService} from './hello.service'
import {UserService} from './User/user.service';
import { Router,RouterOutlet} from '@angular/router'
import {HttpClient} from '@angular/common/http'
import {HeaderComponent} from './core/header/header.component'
import {TeamComponent} from './layout/team/team.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [UserService]
})
export class AppComponent{
  title='Demo';

  // constructor(private helloService: HelloService) {}
  // openHello(){
  //   this.helloService.getHello().subscribe((message)=>{
  //     const newtab=window.open('','_blank');
  //     if (newtab){
  //       newtab.document.writeln(`<h1>${message}</h1>`);
  //     }
  //   });
  // }
  // constructor (private app: UserService, private http: HttpClient, private router: Router){
  //
  // }
  // title='basic-app';
}
