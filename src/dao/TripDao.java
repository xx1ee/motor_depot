package dao;

import entity.Driver;
import entity.Trip;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripDao implements Dao<Integer, Trip>{
    private static final TripDao INSTANCE = new TripDao();
    private static final String DELETE_SQL = """
            DELETE FROM driver WHERE id = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO trip(driver_id, car_id, station_depart, station_arrival, time_depart, time_arrival, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
    private static final String UPDATE_SQL = """
            UPDATE trip
            SET
            driver_id = ?,
            car_id = ?,
            station_depart = ?,
            station_arrival = ?,
            time_depart = ?,
            time_arrival = ?,
            status = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL = """
            SELECT *
            FROM trip
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM trip
            WHERE id = ?
            """;
    private static final String FIND_TRIPS_BY_SERIAL_NUMBER = """
            SELECT *
            FROM trip
            JOIN driver ON trip.driver_id = driver.id 
            WHERE driver.serial_number = ?
            """;
    private static final String FIND_TRIPS_PER_DATE = """
            SELECT * FROM trip WHERE time_arrival between ? AND ?
            """;
    private static final String FIND_TRIPS_DEPART_OF = """
            SELECT * FROM trip WHERE station_depart = ?
            """;
    private static final String SET_STATUS_ON_ZAVERSHEN = """
            update trip set status = 'Завершен' where time_arrival < now();
            
            update car set status = 'Доступна'
            where id in (
                select car_id
                from trip
                where time_arrival < now()
                );
                
            update driver set status = 'Доступен'
            where id in (
                select driver_id
                from trip
                where time_arrival < now()
                )
            """;
    @SneakyThrows
    @Override
    public List<Trip> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                trips.add(buildTrip(resultSet));
            }

            return trips;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Trip> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Trip trip= null;
            if (result.next()) {
                trip = new Trip(
                        result.getInt("id"),
                        DriverDao.getInstance().findById(result.getInt("driver_id")).orElse(null),
                        CarDao.getInstance().findById(result.getInt("car_id")).orElse(null),
                        result.getString("station_depart"),
                        result.getString("station_arrival"),
                        result.getTimestamp("time_depart").toLocalDateTime(),
                        result.getTimestamp("time_arrival").toLocalDateTime(),
                        result.getString("status")
                );
            }
            return Optional.ofNullable(trip);
        }
    }
    @SneakyThrows
    public List<Trip> findBySerialNumber(String serial) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_TRIPS_BY_SERIAL_NUMBER)) {
            preparedStatement.setString(1, serial);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                trips.add(buildTrip(resultSet));
            }

            return trips;
        }
    }

    @SneakyThrows
    public List<Trip> findPerDate(LocalDateTime startDate, LocalDateTime endDate) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_TRIPS_PER_DATE)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                trips.add(buildTrip(resultSet));
            }

            return trips;
        }
    }
    @SneakyThrows
    public List<Trip> findTripsDepartOf(String depart_st) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_TRIPS_DEPART_OF)) {
            preparedStatement.setString(1, depart_st);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trip> trips = new ArrayList<>();

            while (resultSet.next()) {
                trips.add(buildTrip(resultSet));
            }

            return trips;
        }
    }


    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
    @SneakyThrows
    public boolean zaversh() {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SET_STATUS_ON_ZAVERSHEN)) {
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    @SneakyThrows
    public void update(Trip entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, entity.getDriver().getId());
            preparedStatement.setInt(2, entity.getCar().getId());
            preparedStatement.setString(3, entity.getSt_depart());
            preparedStatement.setString(4, entity.getSt_arr());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(entity.getTime_depart()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(entity.getTime_arr()));
            preparedStatement.setInt(7, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Trip save(Trip entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getDriver().getId());
            preparedStatement.setInt(2, entity.getCar().getId());
            preparedStatement.setString(3, entity.getSt_depart());
            preparedStatement.setString(4, entity.getSt_arr());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(entity.getTime_depart()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(entity.getTime_arr()));
            preparedStatement.setString(7, entity.getStatus());
            System.out.println(preparedStatement.executeUpdate() > 0);
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }
    public static TripDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Trip buildTrip(ResultSet resultSet) {
        return new Trip(
                resultSet.getObject("id", Integer.class),
                DriverDao.getInstance().findById(resultSet.getObject("driver_id", Integer.class)).orElse(null),
                CarDao.getInstance().findById(resultSet.getObject("car_id", Integer.class)).orElse(null),
                resultSet.getObject("station_depart", String.class),
                resultSet.getObject("station_arrival", String.class),
                resultSet.getObject("time_depart", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("time_arrival", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("status", String.class)
        );
    }
}
