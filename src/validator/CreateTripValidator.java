package validator;

import dto.CreateTripDto;
import lombok.Getter;
import util.LocalDateTimeFormatter;

public class CreateTripValidator implements Validator<CreateTripDto>{
    @Getter
    private static CreateTripValidator Instance = new CreateTripValidator();
    @Override
    public ValidationResult isValid(CreateTripDto obj) {
        var validationResult = new ValidationResult();
        if (!obj.getStatus().equals("Зарегестрирован") && !obj.getStatus().equals("Исполняется") && !obj.getStatus().equals("Завершен")) {
            validationResult.add(Error.of("5", "неверный статус"));
        }
        if (!LocalDateTimeFormatter.isValid(obj.getTime_depart())) {
            validationResult.add(Error.of("7", "дата отправления введена неверно"));
        }
        if (!LocalDateTimeFormatter.isValid(obj.getTime_arr())) {
            validationResult.add(Error.of("7", "дата прибытия введена неверно"));
        }
        return validationResult;
    }
}
