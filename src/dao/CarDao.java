package dao;

import entity.Car;
import entity.Driver;
import lombok.AccessLevel;
import util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarDao implements Dao<Integer, Car> {

    private static final CarDao INSTANCE = new CarDao();
    private static final String DELETE_SQL = """
            DELETE FROM car WHERE id = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO car(model, number, status)
                VALUES (?, ?, ?, ?, ?)
                """;
    private static final String UPDATE_SQL = """
            UPDATE car
            SET
            model = ?,
            number = ?,
            status
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT *
            FROM car
            """;
    public static final String FIND_BY_ID_SQL = """
            SELECT id, model, number, status
            FROM car
            WHERE id = ?
            """;

    @Override
    @SneakyThrows
    public List<Car> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> flights = new ArrayList<>();
            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Car> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Car car= null;
            if (result.next()) {
                car = new Car(
                        result.getInt("id"),
                        result.getString("model"),
                        result.getString("number"),
                        result.getString("status")
                );
            }
            return Optional.ofNullable(car);
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

    @Override
    @SneakyThrows
    public void update(Car entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getModel());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setString(3, entity.getStatus());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Car save(Car entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getModel());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setString(3, entity.getStatus());
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }

    private Car buildFlight(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("model", String.class),
                resultSet.getObject("number", String.class),
                resultSet.getObject("status", String.class)
        );
    }
}
