package MidtermExam.Group2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Validated
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotNull(message = "Price is mandatory")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDate createdTime;

    @Column(name = "updated_time", nullable = false)
    private LocalDate updatedTime;

    @PrePersist
    public void prePersist() {
        if (createdTime == null) {
            createdTime = LocalDate.now();
        }
        if (updatedTime == null) {
            updatedTime = LocalDate.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedTime = LocalDate.now();
    }

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade =
    // CascadeType.ALL)
    // private List<InvoiceProduct> invoiceProducts = new ArrayList<>();
}
