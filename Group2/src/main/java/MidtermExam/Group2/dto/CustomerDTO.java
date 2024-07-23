package MidtermExam.Group2.dto;

import MidtermExam.Group2.entity.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private UUID id;
    private String name;
    private String phoneNumber;
    private Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
