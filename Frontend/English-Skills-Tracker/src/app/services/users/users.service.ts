import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiUrl = 'http://localhost:8080/users';
  constructor(private http: HttpClient) { }

  getUsers():Observable<any>{
    return this.http.get<any>(this.apiUrl);
  }

  createUser(newUser:any):Observable<any>{

    console.log(newUser);
    
    return this.http.post<any>(this.apiUrl,newUser);
  }

  deleteUser(id:number):Observable<any>{

    return this.http.delete<any>(`${this.apiUrl}`+`/${id}`);
  }
}
