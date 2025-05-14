import { Component } from '@angular/core';
import {User} from '../User/user';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {

  protected readonly User = User;
}
