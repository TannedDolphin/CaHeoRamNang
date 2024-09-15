import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const DICHVU_API = "http://localhost:8080/api/dichvus"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DichVuService {

  constructor(private http: HttpClient) {}

  // Tạo mới dịch vụ
  createDichVu(dichVuRequest: any): Observable<any> {
    return this.http.post(`${DICHVU_API}/create`, dichVuRequest);
  }

  // Cập nhật dịch vụ
  updateDichVu(id: number, dichVuRequest: any): Observable<any> {
    return this.http.put(`${DICHVU_API}/update/${id}`, dichVuRequest);
  }

  // Xóa dịch vụ
  deleteDichVu(id: number): Observable<void> {
    return this.http.delete<void>(`${DICHVU_API}/delete/${id}`);
  }

  // Lấy danh sách tất cả dịch vụ
  getAllDichVu(): Observable<any> {
    return this.http.get(`${DICHVU_API}/list`);
  }

  // Lấy dịch vụ theo ID
  getDichVuById(id: number): Observable<any> {
    return this.http.get(`${DICHVU_API}/${id}`);
  }

  // Tìm kiếm dịch vụ theo tên
  getDichVuByName(name: string): Observable<any> {
    return this.http.get(`${DICHVU_API}/search?name=${name}`);
  }
}
