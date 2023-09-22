package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDriverDto {
    private Integer id;
    private String name;
    private String birth;
    private String serial_number;
    private String status;
}
