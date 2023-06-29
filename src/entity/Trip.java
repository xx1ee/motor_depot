package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    private Integer id;
    private Driver driver;
    private Car car;
    private String st_depart;
    private String st_arr;
    private LocalDateTime time_depart;
    private LocalDateTime time_arr;
    private String status;
}
