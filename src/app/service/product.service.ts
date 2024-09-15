import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const PRODUCT_API = "http://localhost:8080/api/product"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {}

  // Tạo mới sản phẩm
  createProduct(productRequest: any): Observable<any> {
    return this.http.post(PRODUCT_API + '/create', productRequest);
  }

  // Cập nhật sản phẩm theo ID
  updateProduct(id: number, productRequest: any): Observable<any> {
    return this.http.put(`${PRODUCT_API}/update/${id}`, productRequest);
  }

  // Xóa sản phẩm theo ID
  deleteProduct(id: number): Observable<string> {
    return this.http.delete<string>(`${PRODUCT_API}/delete/${id}`);
  }

  // Lấy danh sách tất cả sản phẩm
  getAllProducts(): Observable<any> {
    return this.http.get(`${PRODUCT_API}/all`);
  }

  // Tìm sản phẩm theo ID
  getProductById(id: number): Observable<any> {
    return this.http.get(`${PRODUCT_API}/findById/${id}`);
  }

  // Tìm sản phẩm theo tên
  getProductByName(name: string): Observable<any> {
    return this.http.get(`${PRODUCT_API}/findByName/${name}`);
  }
}
