import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const USER_API = "http://localhost:8080/api/user"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  // Tạo mới user - Truyền thẳng dữ liệu dưới dạng object
  createUser(request: { username: string, password: string, email: string, fullname: string, phone?: string }): Observable<any> {
    return this.http.post<any>(USER_API, request);
  }

  // Lấy danh sách tất cả user - Không sử dụng model
  getUsers(): Observable<any> {
    return this.http.get<any>(USER_API);
  }

  // Lấy thông tin người dùng hiện tại - Không qua model
  getMyInfo(): Observable<any> {
    return this.http.get<any>(`${USER_API}/my-info`);
  }

  // Lấy thông tin user theo ID - Truyền thẳng dữ liệu
  getUser(userId: string): Observable<any> {
    return this.http.get<any>(`${USER_API}/${userId}`);
  }

  // Cập nhật user theo ID - Truyền thẳng request object
  updateUser(userId: string, request: { password?: string, email?: string, fullname?: string, phone?: string }): Observable<any> {
    return this.http.put<any>(`${USER_API}/${userId}`, request);
  }

  // Xóa user theo ID - Không cần model cho phản hồi
  deleteUser(userId: string): Observable<any> {
    return this.http.delete<any>(`${USER_API}/${userId}`);
  }
}
