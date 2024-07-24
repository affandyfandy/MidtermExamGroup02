package MidtermExam.Group2.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InvoicesDTO {

    private UUID id;

    private UUID customerId;

    @DecimalMin(value = "0.0", inclusive = true, message = "Invoice amount must be greater than or equal to 0")
    private BigDecimal invoiceAmount;

    @NotNull(message = "Invoice date cannot be null")
    private LocalDate invoiceDate;
}

