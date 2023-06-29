package dao;

import entity.Car;
import entity.Users;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersDao implements  Dao<Long, Users>{
    private static final UsersDao INSTANCE = new UsersDao();

    private static final String FIND_ALL = """
            SELECT *
            FROM users
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
    public Optional<Users> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Users entity) {

    }

    @Override
    public Users save(Users entity) {
        return null;
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
