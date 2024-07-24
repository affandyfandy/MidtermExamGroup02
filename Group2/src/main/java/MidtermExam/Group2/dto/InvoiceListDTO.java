package MidtermExam.Group2.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceListDTO {

    @NotNull(message = "Invoice id cannot be null")
    private UUID id;

    @NotNull(message = "Invoice amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Invoice amount must be greater than or equal to 0")
    private BigDecimal invoiceAmount;

    @NotNull(message = "Customer name cannot be null")
    private String customerName;

    @NotNull(message = "Invoice date cannot be null")
    private LocalDate invoiceDate;

}
