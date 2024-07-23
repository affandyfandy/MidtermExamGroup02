package MidtermExam.Group2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoice_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(InvoiceProductId.class)
public class InvoiceProduct {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Invoice cannot be null")
    private Invoice invoice;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Product cannot be null")
    private Product product;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be greater than or equal to 0")
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    @NotNull(message = "Created at cannot be null")
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    @NotNull(message = "Updated at cannot be null")
    private LocalDate updatedAt;
}
