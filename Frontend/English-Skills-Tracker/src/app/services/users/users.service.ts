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

  createUser():Observable<any>{
    return this.http.post<any>(this.apiUrl,{
      "name": "paula",
      "email": "paula@d.com",
      "status":"ACTIVE",
      "password": "Dandsaiii",
      "role":"ADMIN"

    });
  }

  deleteUser(id:number):Observable<any>{

    return this.http.delete<any>(`${this.apiUrl}`+`/${id}`);
  }
}
