package MidtermExam.Group2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceAddDTO {

    private UUID invoiceId;
    private UUID customerId;
    private BigDecimal invoiceAmount;
    private LocalDate invoiceDate;
    private List<InvoiceProductDTO> products;
}
