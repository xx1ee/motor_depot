package dao;

import entity.Driver;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverDao implements Dao<Integer, Driver> {
    private static final DriverDao INSTANCE = new DriverDao();

    private static final String DELETE_SQL = """
            DELETE FROM driver WHERE id = ?
            """;
    private static final String DELETE_BY_SERIAL_NUM_SQL = """
            DELETE FROM driver WHERE serial_number = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO driver(name, birth, serial_number, status)
                VALUES (?, ?, ?, ?)
                """;
    private static final String UPDATE_SQL = """
            UPDATE driver
            SET
            name = ?,
            birth = ?,
            serial_number = ?,
            status = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT *
            FROM driver
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT id, name, birth, serial_number, status
            FROM driver
            WHERE id = ?
            """;
    private static final String FIND_FREE_DRIVERS = """
            SELECT id, name, birth, serial_number, status
            FROM driver
            WHERE status = 'Доступен'
            """;
    private static final String SET_STATUS_DRIVERS = """
            update driver set status = 'Доступен'
            where id in (
                select driver_id
                from trip
                where time_arrival < now()
                )
            """;
    private static final String FIND_BY_SERIAL_NUM_SQL = """
    SELECT id, name, birth, serial_number, status
            FROM driver
            WHERE serial_number = ?
    """;
    @SneakyThrows
    @Override
    public List<Driver> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();

            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }

            return drivers;
        }
    }
    @SneakyThrows
    public List<Driver> findFree() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_FREE_DRIVERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();

            while (resultSet.next()) {
                drivers.add(buildDriver(resultSet));
            }

            return drivers;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Driver> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Driver driver= null;
            if (result.next()) {
                driver = new Driver(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth").toLocalDate(),
                        result.getString("serial_number"),
                        result.getString("status")
                );
            }
            return Optional.ofNullable(driver);
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
    public boolean delete(String id) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(DELETE_BY_SERIAL_NUM_SQL)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    @SneakyThrows
    public void update(Driver entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, Date.valueOf(entity.getBirth()));
            preparedStatement.setString(3, entity.getSerial_number());
            preparedStatement.setString(4, entity.getStatus());
            preparedStatement.setInt(5, entity.getId());
            System.out.println(preparedStatement.executeUpdate());
        }
    }

    @Override
    @SneakyThrows
    public Driver save(Driver entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, Date.valueOf(entity.getBirth()));
            preparedStatement.setString(3, entity.getSerial_number());
            preparedStatement.setString(4, entity.getStatus());
            System.out.println(preparedStatement.executeUpdate() > 0);
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }
    @SneakyThrows
    public boolean setStatusDrivers() {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(SET_STATUS_DRIVERS)) {
            return preparedStatement.executeUpdate() > 0;
        }
    }
    public static DriverDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Driver buildDriver(ResultSet resultSet) {
        return new Driver(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("birth", LocalDate.class),
                resultSet.getObject("serial_number", String.class),
                resultSet.getObject("status", String.class)
        );
    }
    @SneakyThrows
    public Optional<Driver> findBySerialNum(String serianNum) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_SERIAL_NUM_SQL)) {
            statement.setString(1, serianNum);
            var result = statement.executeQuery();
            Driver driver= null;
            if (result.next()) {
                driver = new Driver(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birth").toLocalDate(),
                        result.getString("serial_number"),
                        result.getString("status")
                );
            }
            return Optional.ofNullable(driver);
        }
    }
}
