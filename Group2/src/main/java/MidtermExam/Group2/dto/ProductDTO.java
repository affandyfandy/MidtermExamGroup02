package MidtermExam.Group2.dto;

import MidtermExam.Group2.entity.Status;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Status status;
    private LocalDate created_at;
    private LocalDate updated_at;
}
