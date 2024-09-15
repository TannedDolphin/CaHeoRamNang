import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const ORDER_API = "http://localhost:8080/api/order"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {}

  // Tạo mới đơn hàng
  createOrder(orderRequest: any): Observable<any> {
    return this.http.post(`${ORDER_API}/create`, orderRequest);
  }

  // Cập nhật đơn hàng theo ID
  updateOrder(id: number, orderRequest: any): Observable<any> {
    return this.http.put(`${ORDER_API}/update/${id}`, orderRequest);
  }

  // Xóa đơn hàng theo ID
  deleteOrder(id: number): Observable<string> {
    return this.http.delete<string>(`${ORDER_API}/delete/${id}`);
  }

  // Lấy danh sách tất cả đơn hàng
  getAllOrders(): Observable<any> {
    return this.http.get(`${ORDER_API}/all`);
  }

  // Tìm đơn hàng theo ID
  getOrderById(id: number): Observable<any> {
    return this.http.get(`${ORDER_API}/findById/${id}`);
  }
}
