package validator;

import dto.CreateCarDto;
import dto.CreateDriverDto;
import lombok.Getter;
import util.LocalDateFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDriverValidator implements Validator<CreateDriverDto>{
    private static final Pattern VALID_NAME = Pattern.compile("[A-Za-zА-Яа-я]+ [A-Za-zА-Яа-я]+( [A-Za-zА-Яа-я]+)?", Pattern.CASE_INSENSITIVE);
    @Getter
    private static CreateDriverValidator Instance = new CreateDriverValidator();
    @Override
    public ValidationResult isValid(CreateDriverDto obj) {
        var validationResult = new ValidationResult();
        Matcher matcher = VALID_NAME.matcher(obj.getName());
        if (!obj.getStatus().equals("Доступен") && !obj.getStatus().equals("Занят")) {
            validationResult.add(Error.of("5", "неверный статус"));
        }
        if (!matcher.find()) {
            validationResult.add(Error.of("6", "имя должно содержать только буквы"));
        }
        if (!LocalDateFormatter.isValid(obj.getBirth())) {
            validationResult.add(Error.of("7", "дата введена неверно"));
        }
        return validationResult;
    }
}
