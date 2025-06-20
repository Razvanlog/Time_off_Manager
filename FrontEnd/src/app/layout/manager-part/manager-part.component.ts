import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterModule} from '@angular/router';
import {TimeOffRequest} from '../../services/requests-api.service';
import {RequestsApiService} from '../../services/requests-api.service';
import {User} from '../../User/user';
import {UserResponse} from '../../services/user-api.service';
import {StoredUser} from '../../services/auth.service';
import {Observable} from 'rxjs';
import {FormsModule} from '@angular/forms';

export class managerInj{
  userId?: number;
  name?: string;
  email: string;
  role: string;
}

@Component({
  selector: 'app-manager-part',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './manager-part.component.html',
  styleUrl: './manager-part.component.scss',
  providers: [RequestsApiService]
})
export class ManagerPartComponent {
  statusOptions: string[] = ['Accept','Reject','NA'];
  requests: TimeOffRequest[] = [];
  user: managerInj=new managerInj();
  loading: boolean=true;
  private readonly USER_STORAGE_KEY = 'timeOffAppUser';
  constructor(private requestService: RequestsApiService){
    this.ngOnClick();
  }
  ngOnClick(): void{
    const userString=localStorage.getItem(this.USER_STORAGE_KEY);
    if (userString){
      try{
        const parsedUser=JSON.parse(userString) as StoredUser;
        console.log(parsedUser);
        this.user.email=parsedUser.email;
        this.user.name=parsedUser.name;
        this.user.role=parsedUser.role;
      }
      catch{

      }
    }
    this.requestService.get(this.user).subscribe(
      (data) =>{
        this.requests=data;
        localStorage.setItem("requests",JSON.stringify(data));
        this.loading=false;
      }
    )
  }
  onRowSubmit(request: TimeOffRequest): void{
    request.currentStatus=request.selectedStatus;
    // console.log(request.currentStatus);
    if (request.currentStatus=`Accept`){
      this.requestService.acceptRequest(request).subscribe(
        (data) =>{
          console.log(`${data} accept`);
        }
      )
    }
    else{
      this.requestService.rejectRequest(request).subscribe(
        (data) =>{
          console.log(`${data} reject`);
        }
      )
    }
  }
  // fetchData(): void{
  //   this.user=JSON.parse(localStorage.getItem('data')||'""');
  //   this.requestService.get(this.user).subscribe(
  //     (data) =>{
  //       this.requests=data;
  //       console.log("YESYESYES");
  //       localStorage.setItem("requests",JSON.stringify(data));
  //       this.loading=false;
  //     }
  //   )
  // }
}
