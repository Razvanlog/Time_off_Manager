import { Injectable, inject} from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { User } from './user'
import {Observable} from 'rxjs';
@Injectable(
  {
    providedIn: 'root',
  }
)
export class UserService {
  private usersUrl="http://localhost:8080/user";
  private http=inject(HttpClient);
  // private http: HttpClient;
  // costructor(private http: HttpClient){  }
  public findAll(): Observable<User[]>{
    // console.log(this.usersUrl)
    return this.http.get<User[]>(`${this.usersUrl}`);
  }
  public save(user: User){
    return this.http.post<User[]>(`${this.usersUrl}`, user);
  }
  constructor() { }
}
