package MidtermExam.Group2.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDetailDTO {

    private UUID invoiceId;
    private BigDecimal invoiceAmount;
    private LocalDateTime invoiceDate;

    private CustomerDTO customer;

    private List<InvoiceProductWithoutIdDTO> products;
}