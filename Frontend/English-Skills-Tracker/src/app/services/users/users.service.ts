import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Users } from '../../models/users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiUrl = 'http://localhost:8080/users';//end point backend 
  private userSubject = new BehaviorSubject<Users[]>([]); // Instancia de objeto para actualizar componente

  constructor(private http: HttpClient) { }

  getUsers():Observable<Users[]>{
    return this.http.get<Users[]>(this.apiUrl).pipe(// flujo de datos para actulizar componentes
      tap((users:Users[])=>{
        this.userSubject.next(users);
      })
    );
  }

  createUser(newUser:Users):Observable<Users>{

    console.log(newUser);
    
    return this.http.post<Users>(this.apiUrl,newUser).pipe(
      tap(()=>{
        this.getUsers().subscribe();
      })
    );
  }

  deleteUser(id:number):Observable<Users>{

    return this.http.delete<Users>(`${this.apiUrl}`+`/${id}`).pipe(
      tap(()=>{
        this.getUsers().subscribe();
      })
    );
  }

  getUsersObservable(): Observable<Users[]> {
    return this.userSubject.asObservable();
  }
}
