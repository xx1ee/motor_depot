package util;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@UtilityClass
public class LocalDateTimeFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LocalDateTime format(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public boolean isValid(String dateTime) {
        try {
            return Optional.ofNullable(dateTime)
                    .map(LocalDateTimeFormatter::format)
                    .isPresent();
        } catch (DateTimeException e) {
            return false;
        }
    }
}
