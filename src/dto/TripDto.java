package dto;

import entity.Car;
import entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDto {
    private Integer id;
    private String description;
    private CarDto carDto;
    private DriverDto driverDto;
}
