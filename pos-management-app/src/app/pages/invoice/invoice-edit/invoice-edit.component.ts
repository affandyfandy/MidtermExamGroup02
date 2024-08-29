import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InvoiceService } from '../../../services/invoice.service';
import { ActivatedRoute, Router } from '@angular/router'
import { getCurrentTimestamp } from '../../../core/util/date-time.util';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-invoice-edit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './invoice-edit.component.html',
  styleUrl: './invoice-edit.component.scss'
})
export class InvoiceEditComponent {
  @Input() invoice: any;
  @Output() invoiceUpdated = new EventEmitter<any>();

  selectedProductIndex: number | null = null;

  constructor(
    private invoiceService: InvoiceService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const invoiceId = this.route.snapshot.paramMap.get('id');
    if (invoiceId) {
      this.invoiceService.get(invoiceId).subscribe(invoice => {
        this.invoice = invoice;
      });
    }
  }

  onProductChange(index: number): void {
    this.selectedProductIndex = index;
  }

  onQuantityChange(): void {
    if (this.selectedProductIndex !== null) {
      const product = this.invoice.products[this.selectedProductIndex];
      product.amount = product.quantity * product.price;
      this.updateInvoiceAmount();
    }
  }

  updateInvoiceAmount(): void {
    let totalAmount = 0;
    this.invoice.products.forEach((product: any) => {
      totalAmount += product.amount;
    });
    this.invoice.invoiceAmount = totalAmount;
  }

  updateInvoice(): void {
    this.invoice.updatedTime = getCurrentTimestamp();
    this.invoice.products.forEach((product: any) => {
      this.invoiceService.update(this.invoice.invoiceId, product.productId, product)
        .subscribe({
          next: (updatedProduct) => {
            this.invoiceUpdated.emit(updatedProduct);
            alert("Invoice Updated");
            this.router.navigate(['/invoice']);
          },
          error: (error) => {
            console.error('Error updating product:', error);
          }
        });
    });
  }

  deleteInvoice(): void {
    if (confirm('Do you want to delete this invoice?')) {
      this.invoiceService.deleteInvoice(this.invoice.invoiceId).subscribe(() => {
        alert("Invoice deleted!");
        this.router.navigate(['/invoice']);
      });
    }
  }
}
