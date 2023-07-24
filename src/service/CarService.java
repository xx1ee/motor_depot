package service;

import dao.CarDao;
import dto.CarDto;
import dto.CreateCarDto;
import dto.CreateUsersDto;
import entity.Car;
import entity.Users;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import validator.CreateCarValidator;
import validator.CreateUserValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarService {
    private final CarDao carDao = CarDao.getInstance();
    private final static CarService INSTANCE = new CarService();

    @SneakyThrows
    public void save(CreateCarDto createCarDto) {
        var validationResult = CreateCarValidator.getInstance().isValid(createCarDto);
        if (validationResult.isValid()) {
            Car car = new Car();
            car.setModel(createCarDto.getModel());
            car.setNumber(createCarDto.getNumber());
            car.setStatus(createCarDto.getStatus());
            System.out.println(carDao.save(car));
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public List<CarDto> findAll() {
        return carDao.findAll().stream()
                .map(car -> build(car)).collect(Collectors.toList());
    }
    public CarDto findById(Integer id) {
        return build(Objects.requireNonNull(carDao.findById(id).orElse(null)));
    }
    public CarDto findBySerialNum(String serialNum) {
        return build(Objects.requireNonNull(carDao.findBySerialNum(serialNum).orElse(null)));
    }

    public List<CarDto> findFreeCar() {
        return carDao.findFreeCar().stream()
                .map(car -> build(car)).collect(Collectors.toList());
    }
    public void deleteCar(String serialNum) {
        carDao.delete(serialNum);
    }
    public boolean setStatusCars() {
        return carDao.setStatusCars();
    }

    public CarDto build(Car car) {
        return new CarDto(car.getId(),
                """
                        модель авто - %s,
                        номера - %s,
                        статус - %s
                        """.formatted(car.getModel(), car.getNumber(), car.getStatus()));
    }

    public static CarService getInstance() {
        return INSTANCE;
    }
}
