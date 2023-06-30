package dao;

import entity.Car;
import entity.Trip;
import entity.Users;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersDao implements  Dao<Integer, Users>{
    private static final UsersDao INSTANCE = new UsersDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM users
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users WHERE id = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO users(email, password, role, image)
                VALUES (?, ?, ?, ?)
                """;
    private static final String UPDATE_SQL = """
            UPDATE users
            SET
            email = ?,
            password = ?,
            role = ?,
            image = ?
            WHERE id = ?
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM users
            WHERE id = ?
            """;
    @SneakyThrows
    @Override
    public List<Users> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(buildUsers(resultSet));
            }

            return users;
        }
    }
    @Override
    @SneakyThrows
    public Optional<Users> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Users users = null;
            if (result.next()) {
                users = new Users(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getString("image")
                );
            }
            return Optional.ofNullable(users);
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
    public void update(Users entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.setString(4, entity.getImage());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public Users save(Users entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole());
            preparedStatement.setString(4, entity.getImage());
            System.out.println(preparedStatement.executeUpdate() > 0);
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            }
            return entity;
        }
    }

    public static UsersDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Users buildUsers(ResultSet resultSet) {
        return new Users(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("role", String.class),
                resultSet.getObject("image", String.class)
        );
    }

}
