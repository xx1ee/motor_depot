package validator;

import dto.CreateCarDto;
import dto.CreateUsersDto;
import lombok.Getter;

public class CreateCarValidator implements Validator<CreateCarDto>{
    @Getter
    private static CreateCarValidator Instance = new CreateCarValidator();
    @Override
    public ValidationResult isValid(CreateCarDto obj) {
        var validationResult = new ValidationResult();
        if (!obj.getStatus().equals("Доступна") && !obj.getStatus().equals("Занята")) {
            validationResult.add(Error.of("4", "неверный статус"));
        }
        return validationResult;
    }
}
