import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const AUTH_API = "http://localhost:8080/api/auth"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // Gửi request AuthenticationRequest
  authenticate(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'token', { username, password }, httpOptions);
  }

  // Gửi request IntrospectRequest
  introspect(token: string): Observable<any> {
    return this.http.post(AUTH_API + 'introspect', { token }, httpOptions);
  }

  // Gửi request LogoutRequest
  logout(token: string): Observable<any> {
    return this.http.post(AUTH_API + 'logout', { token }, httpOptions);
  }
}
