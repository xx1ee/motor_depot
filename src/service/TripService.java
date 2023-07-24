package service;

import dao.CarDao;
import dao.DriverDao;
import dao.TripDao;
import dao.UsersDao;
import dto.*;
import entity.Car;
import entity.Driver;
import entity.Trip;
import exception.ValidationException;
import lombok.*;
import util.LocalDateTimeFormatter;
import validator.CreateCarValidator;
import validator.CreateTripValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TripService {
    private final TripDao tripDao = TripDao.getInstance();
    private final CarDao carDao = CarDao.getInstance();
    private final DriverDao driverDao = DriverDao.getInstance();
    private final static TripService INSTANCE = new TripService();

    public TripDto findById(Integer id) {
        return build(Objects.requireNonNull(tripDao.findById(id).orElse(null)));}
    public void delete(Integer id) {
        tripDao.delete(id);
    }

    public List<TripDto> findAll() {
        return tripDao.findAll().stream()
                .map(trip ->build(trip)).collect(Collectors.toList());
    }
    @SneakyThrows
    public void save(CreateTripDto createTripDto) {
        var validationResult = CreateTripValidator.getInstance().isValid(createTripDto);
        if (validationResult.isValid()) {
            Trip trip = new Trip();
            trip.setStatus(createTripDto.getStatus());
            trip.setSt_arr(createTripDto.getSt_arr());
            trip.setSt_depart(createTripDto.getSt_depart());
            trip.setTime_arr(LocalDateTimeFormatter.format(createTripDto.getTime_arr()));
            trip.setTime_depart(LocalDateTimeFormatter.format(createTripDto.getTime_depart()));
            trip.setCar(carDao.findById(createTripDto.getCar()).orElse(null));
            trip.setDriver(driverDao.findById(createTripDto.getDriver()).orElse(null));
            System.out.println(tripDao.save(trip));
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }
    public List<TripDto> findBySerialNumber(String serial) {
        return tripDao.findBySerialNumber(serial).stream()
                .map(trip ->build(trip)).collect(Collectors.toList());
    }
    public List<TripDto> findPerDate(String startDate, String endDate) {
        return tripDao.findPerDate(LocalDateTimeFormatter.format(startDate), LocalDateTimeFormatter.format(endDate)).stream()
                .map(trip ->build(trip)).collect(Collectors.toList());
    }
    public List<TripDto> findTripsDepartOf(String depart_st) {
        return tripDao.findTripsDepartOf(depart_st).stream()
                .map(trip ->build(trip)).collect(Collectors.toList());
    }
    public TripDto build(Trip trip) {
        return new TripDto(trip.getId(),
                """
                        Время отправления - %s,
                        Время прибытия - %s,
                        Станция отправления - %s,
                        Станция прибытия - %s,
                        Статус рейса - %s
                        """.formatted(trip.getTime_depart(), trip.getTime_arr() ,trip.getSt_depart(), trip.getSt_arr(), trip.getStatus()),
                CarService.getInstance().build(trip.getCar()),
                DriverService.getInstance().build(trip.getDriver()));
    }
    public void zaversh() {
        tripDao.zaversh();
    }

    public static TripService getInstance() {
        return INSTANCE;
    }
}
