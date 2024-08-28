import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Invoice } from '../models/invoice';
import { InvoiceProduct } from '../models/invoice-product';

const baseUrl = 'http://localhost:8080/api/v1/invoices';
const baseUrlProduct = 'http://localhost:8080/api/v1/invoice-products';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  constructor(private http : HttpClient) {}

  getInvoices(): Observable<Invoice[]> {
    return this.http.get<any>(baseUrl)
      .pipe(map(response => response.content));
  }

  get(id: any): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  update(invoiceId: any, productId: any, data: any): Observable<any> {
    return this.http.put(`${baseUrlProduct}/${invoiceId}/${productId}`, data);
  }
  updateInvoice(invoiceId: any, invoice: any): Observable<any> {
    return this.http.put(`${baseUrl}/${invoiceId}`, invoice);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  createInvoiceProduct(invoiceProduct: InvoiceProduct): Observable<InvoiceProduct> {
    return this.http.post<InvoiceProduct>(`${baseUrl}`, invoiceProduct);
  }

  deleteInvoice(id: string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }

  getInvoiceProducts(invoiceId: string): Observable<InvoiceProduct[]> {
    return this.http.get<InvoiceProduct[]>(`${baseUrl}/${invoiceId}/products`);
  }
}
