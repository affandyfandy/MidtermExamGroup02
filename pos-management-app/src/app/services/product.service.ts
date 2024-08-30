import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Product } from '../models/product.model';

const baseUrl = 'http://localhost:8080/api/v1/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http : HttpClient) {}

  // getAll(): Observable<Product[]> {
  //   return this.http.get<any>(baseUrl).pipe(
  //     map(response => response.content)
  //   );
  // }

  getAll(): Observable<Product[]> {
    return this.http.get<any>(baseUrl + "/list").pipe(
      map(response => response)
    );
  }

  get(id: any): Observable<Product> {
    return this.http.get<Product>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  uploadFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post(baseUrl + '/import', formData, {responseType: 'text'});
  }
}
