package service;

import dao.CarDao;
import dao.DriverDao;
import dto.CarDto;
import dto.CreateCarDto;
import dto.CreateDriverDto;
import dto.DriverDto;
import entity.Car;
import entity.Driver;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.LocalDateFormatter;
import validator.CreateCarValidator;
import validator.CreateDriverValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverService {
    private final DriverDao driverDao = DriverDao.getInstance();
    private final static DriverService INSTANCE = new DriverService();

    @SneakyThrows
    public void save(CreateDriverDto createDriverDto) {
        var validationResult = CreateDriverValidator.getInstance().isValid(createDriverDto);
        if (validationResult.isValid()) {
            Driver driver = new Driver();
            driver.setName(createDriverDto.getName());
            driver.setBirth(LocalDateFormatter.format(createDriverDto.getBirth()));
            driver.setSerial_number(createDriverDto.getSerial_number());
            driver.setStatus(createDriverDto.getStatus());
            System.out.println(driverDao.save(driver));
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public List<DriverDto> findAll() {
        return driverDao.findAll().stream()
                .map(driver -> build(driver)).collect(Collectors.toList());
    }

    public List<DriverDto> findFree() {
        return driverDao.findFree().stream()
                .map(driver -> build(driver)).collect(Collectors.toList());
    }

    public DriverDto findById(Integer id) {
        return build(Objects.requireNonNull(driverDao.findById(id).orElse(null)));
    }
    public void delete(String serialNum) {
        driverDao.delete(serialNum);
    }
    public boolean setStatusDrivers() {
        return driverDao.setStatusDrivers();
    }
    public DriverDto findBySerialNum(String serianNum) {
        return build(Objects.requireNonNull(driverDao.findBySerialNum(serianNum).orElse(null)));
    }

    public DriverDto build(Driver driver) {
        return new DriverDto(driver.getId(),
                """
                        имя - %s,
                        дата рождения - %s,
                        серийный номер - %s,
                        статус - %s
                        """.formatted(driver.getName(), driver.getBirth(), driver.getSerial_number(), driver.getStatus()));
    }

    public static DriverService getInstance() {
        return INSTANCE;
    }
}
