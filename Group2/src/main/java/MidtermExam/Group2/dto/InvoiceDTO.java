package MidtermExam.Group2.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private UUID id;
    private UUID customerId;
    private BigDecimal invoiceAmount;
    private LocalDate invoiceDate;
}

