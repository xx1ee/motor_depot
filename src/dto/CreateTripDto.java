package dto;

import entity.Car;
import entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTripDto {
    private Integer driver;
    private Integer car;
    private String st_depart;
    private String st_arr;
    private String time_depart;
    private String time_arr;
    private String status;
}
