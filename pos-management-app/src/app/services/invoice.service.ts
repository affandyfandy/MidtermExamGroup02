import { HttpClient, HttpParams } from '@angular/common/http';
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

  exportInvoiceToExcel(customerId: string, month: number, year: number): Observable<Blob> {
    let params = new HttpParams();

    if (customerId) {
      params = params.set('customerId', customerId);
    }

    if (month) {
      params = params.set('month', month.toString());
    }

    if (year) {
      params = params.set('year', year.toString());
    }

    return this.http.get(`${baseUrl}/excel`, { params, responseType: 'blob' });
  }

  exportInvoiceToPdf(id: any): Observable<Blob> {
    return this.http.get(`${baseUrl}/${id}/pdf`, { responseType: 'blob' });
  }
}
