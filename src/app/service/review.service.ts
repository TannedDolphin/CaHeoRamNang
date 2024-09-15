import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const REVIEW_API = "http://localhost:8080/api/review"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root',
})
export class ReviewService {

  constructor(private http: HttpClient) {}

  // Thêm mới review
  createReview(request: any): Observable<any> {
    return this.http.post(`${REVIEW_API}/create`, request);
  }

  // Sửa review theo ID
  updateReview(id: number, request: any): Observable<any> {
    return this.http.put(`${REVIEW_API}/update/${id}`, request);
  }

  // Xóa review theo ID
  deleteReview(id: number): Observable<any> {
    return this.http.delete(`${REVIEW_API}/delete/${id}`, { responseType: 'text' });
  }

  // Lấy tất cả review
  getAllReviews(): Observable<any> {
    return this.http.get(`${REVIEW_API}/all`);
  }

  // Tìm review theo ID
  getReviewById(id: number): Observable<any> {
    return this.http.get(`${REVIEW_API}/findById/${id}`);
  }

  // Tìm review theo sản phẩm (productid)
  getReviewsByProductId(productId: number): Observable<any> {
    return this.http.get(`${REVIEW_API}/findByProduct/${productId}`);
  }
}
