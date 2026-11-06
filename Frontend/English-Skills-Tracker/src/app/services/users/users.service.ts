import { HttpClient } from '@angular/common/http';
import { Injectable, InjectionToken, inject } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { CreateUserPayload, Users } from '../../models/users';
import { environment } from '../../../environments/environment';

export const USERS_API_URL = new InjectionToken<string>('USERS_API_URL', {
  providedIn: 'root',
  factory: () => `${environment.apiUrl}/users`
});

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = inject(USERS_API_URL);
  private readonly userSubject = new BehaviorSubject<Users[]>([]);

  getUsers(): Observable<Users[]> {
    return this.http.get<Users[]>(this.apiUrl).pipe(
      tap((users: Users[]) => {
        this.userSubject.next(users);
      })
    );
  }

  createUser(newUser: CreateUserPayload): Observable<Users> {
    return this.http.post<Users>(this.apiUrl, newUser).pipe(
      tap((createdUser: Users) => {
        const currentUsers = this.userSubject.getValue();
        this.userSubject.next([...currentUsers, createdUser]);
      })
    );
  }

  deleteUser(id: string | number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => {
        const updatedUsers = this.userSubject.getValue().filter(user => user.id !== id);
        this.userSubject.next(updatedUsers);
      })
    );
  }

  editUserId(updatedUser: Users): Observable<Users> {
    return this.http.put<Users>(`${this.apiUrl}/${updatedUser.id}`, updatedUser).pipe(
      tap((savedUser: Users) => {
        const updatedUsers = this.userSubject.getValue().map(user =>
          user.id === savedUser.id ? savedUser : user
        );
        this.userSubject.next(updatedUsers);
      })
    );
  }

  getUsersObservable(): Observable<Users[]> {
    return this.userSubject.asObservable();
  }
}
