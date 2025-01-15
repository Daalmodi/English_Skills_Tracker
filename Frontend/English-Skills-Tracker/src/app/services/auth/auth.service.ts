import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Login } from '../../models/login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginEndPoint ='http://localhost:8080/auth/login'; 
  constructor(private http : HttpClient) { }

  loginUser(userlogged:Login):Observable<any>{
    return this.http.post<Login>(this.loginEndPoint,userlogged);
  }
}
