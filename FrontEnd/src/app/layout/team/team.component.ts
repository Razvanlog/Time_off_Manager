import {Component, OnInit} from '@angular/core';

import { User } from '../../User/user'
import { UserService} from '../../User/user.service';
import {CommonModule} from '@angular/common';
import {NgFor} from '@angular/common';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-team',
  imports: [CommonModule, NgFor],
  templateUrl: './team.component.html',
  styleUrl: './team.component.scss'
})
export class TeamComponent implements OnInit{
  users: User[];
  constructor (private userService: UserService){

  }
  ngOnInit(){
    console.log("enterred here");
    this.userService.findAll().subscribe(data => {
      this.users=data;
    })
  }
}
