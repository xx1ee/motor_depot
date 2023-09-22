package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    private Integer id;
    private String name;
    private LocalDate birth;
    private String serial_number;
    private String status;
}
