import { Component, OnInit } from '@angular/core';
import {HelloService} from './hello.service'
@Component({
  selector: 'app-root',
  template: `<button (click)="openHello()">Open Hello Tab</button>`
})
export class AppComponent{
  constructor(private helloService: HelloService) {}
  openHello(){
    this.helloService.getHello().subscribe((message)=>{
      const newtab=window.open('','_blank');
      if (newtab){
        newtab.document.writeln(`<h1>${message}</h1>`);
      }
    });
  }
}