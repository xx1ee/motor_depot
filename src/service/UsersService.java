package service;

import dao.DriverDao;
import dao.UsersDao;
import dto.CreateUsersDto;
import dto.DriverDto;
import dto.UsersDto;
import entity.Users;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import validator.CreateUserValidator;
import validator.ValidationResult;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersService {
    private final UsersDao usersDao = UsersDao.getInstance();
    private final ImageService imageService = ImageService.getINSTANCE();
    private final static UsersService INSTANCE = new UsersService();

    public List<UsersDto> findAll() {
        return usersDao.findAll().stream()
                .map(user -> new UsersDto(user.getId(),
                        user.getImage(),
                        """
                                email - %s,
                                пароль - %s,
                                роль - %s,
                                """.formatted(user.getEmail(), user.getPassword(), user.getRole()))).collect(Collectors.toList());
    }

    public Optional<Users> findByEmailAndPassword(String email, String password) {
        return usersDao.findByEmailAndPassword(email, password);
    }
    @SneakyThrows
    public void save(CreateUsersDto createUsersDto) {
        var validationResult = CreateUserValidator.getInstance().isValid(createUsersDto);
        if (validationResult.isValid()) {
            Users users = new Users();
            users.setEmail(createUsersDto.getEmail());
            users.setPassword(createUsersDto.getPassword());
            users.setRole(createUsersDto.getRole());
            users.setImage(String.valueOf(createUsersDto.getImage().getSubmittedFileName()));
            imageService.upload(createUsersDto.getImage().getSubmittedFileName(), createUsersDto.getImage().getInputStream());
            System.out.println(usersDao.save(users));
        } else {
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public static UsersService getInstance() {
        return INSTANCE;
    }
}
