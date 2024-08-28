import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../models/user.model";
import { map, tap } from "rxjs/operators";

const baseUrl = 'http://localhost:3000/users';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private isLoggedIn = false;
  
  constructor(private http: HttpClient) {}

  login(username: string, password:string): Observable<boolean> {
    return this.http
    .get<User[]>(`${baseUrl}?username=${username}&password=${password}`)
    .pipe(map((users) => users.length > 0),
    tap((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
      if (isLoggedIn) {
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('username', username);
      }
    })
    );
  }

  logout(): void {
    this.isLoggedIn = false;
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('username');
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn || localStorage.getItem('isLoggedIn') === 'true';
  }
}
