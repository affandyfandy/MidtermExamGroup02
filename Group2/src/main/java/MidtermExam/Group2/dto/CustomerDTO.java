package MidtermExam.Group2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Data;

@Data
public class CustomerDTO {

    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Pattern(regexp = "^\\+62\\d{9,13}$", message = "Phone must be a valid Indonesian number starting with +62")
    private String phoneNumber;

    private String status;

}
