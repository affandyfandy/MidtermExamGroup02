package MidtermExam.Group2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {
    private UUID id;
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
    private String status;
}
