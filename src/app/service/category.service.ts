// src/app/services/category.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const CATEGORY_API = "http://localhost:8080/api/category"; // URL API ở backend
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  createCategory(categoryname: string): Observable<any> {
    return this.http.post<any>(CATEGORY_API, { categoryname }, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  updateCategory(id: number, categoryname: string): Observable<any> {
    return this.http.put<any>(`${CATEGORY_API}/${id}`, { categoryname }, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${CATEGORY_API}/${id}`);
  }

  getAllCategories(): Observable<any[]> {
    return this.http.get<any[]>(CATEGORY_API);
  }

  getCategoryById(id: number): Observable<any> {
    return this.http.get<any>(`${CATEGORY_API}/${id}`);
  }

  getCategoryByName(name: string): Observable<any> {
    return this.http.get<any>(`${CATEGORY_API}/name/${name}`);
  }
}
