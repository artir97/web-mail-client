import {Injectable, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RegisterRequest, LoginRequest, LoginResponse} from './auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080'

  register(registerRequest: RegisterRequest) {
    return this.http.post(`${this.apiUrl}/users`, registerRequest)
  }

  login(loginRequest: LoginRequest) {
    return this.http.post<LoginResponse>(
        'http://localhost:8080/login',
        loginRequest
    )
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
