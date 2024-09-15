import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const FEEDBACK_API = "http://localhost:8080/api/feedbacks"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient) {}

  // Tạo mới feedback
  createFeedback(feedbackRequest: any): Observable<any> {
    return this.http.post(`${FEEDBACK_API}/create`, feedbackRequest);
  }

  // Cập nhật feedback
  updateFeedback(id: number, feedbackRequest: any): Observable<any> {
    return this.http.put(`${FEEDBACK_API}/update/${id}`, feedbackRequest);
  }

  // Xóa feedback
  deleteFeedback(id: number): Observable<void> {
    return this.http.delete<void>(`${FEEDBACK_API}/delete/${id}`);
  }

  // Lấy danh sách tất cả feedbacks
  getAllFeedbacks(): Observable<any> {
    return this.http.get(`${FEEDBACK_API}/list`);
  }

  // Lấy feedback theo ID
  getFeedbackById(id: number): Observable<any> {
    return this.http.get(`${FEEDBACK_API}/${id}`);
  }
}
