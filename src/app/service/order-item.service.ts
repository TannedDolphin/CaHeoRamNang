import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';


const ORDERITEM_API = "http://localhost:8080/api/order-items"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  constructor(private http: HttpClient) {}

  // Tạo mới OrderItem
  createOrderItem(orderItemRequest: any): Observable<any> {
    return this.http.post(`${ORDERITEM_API}/create`, orderItemRequest);
  }

  // Cập nhật OrderItem theo ID
  updateOrderItem(id: number, orderItemRequest: any): Observable<any> {
    return this.http.put(`${ORDERITEM_API}/update/${id}`, orderItemRequest);
  }

  // Xóa OrderItem theo ID
  deleteOrderItem(id: number): Observable<void> {
    return this.http.delete<void>(`${ORDERITEM_API}/delete/${id}`);
  }

  // Lấy danh sách tất cả OrderItem
  getAllOrderItems(): Observable<any> {
    return this.http.get(`${ORDERITEM_API}/list`);
  }

  // Lấy OrderItem theo ID
  getOrderItemById(id: number): Observable<any> {
    return this.http.get(`${ORDERITEM_API}/${id}`);
  }
}
