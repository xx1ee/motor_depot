package validator;

import dto.CreateUsersDto;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserValidator implements Validator<CreateUsersDto>{

    private static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Getter
    private static CreateUserValidator Instance = new CreateUserValidator();
    @Override
    public ValidationResult isValid(CreateUsersDto obj) {
        var validationResult = new ValidationResult();
        Matcher matcher = VALID_EMAIL.matcher(obj.getEmail());
        if (!matcher.find()) {
            validationResult.add(Error.of("1", "неверный формат email"));
        }
        if (obj.getPassword().length() < 8) {
            validationResult.add(Error.of("2", "короткий пароль"));
        }
        if (!obj.getRole().equals("ADMIN") && !obj.getRole().equals("USER")) {
            validationResult.add(Error.of("3", "роль должна быть ADMIN или USER"));
        }
        return validationResult;
    }
}
