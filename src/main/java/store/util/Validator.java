package store.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {

    public static int validateNum(String num) {
        return validateNegative(validateIsInteger(num));
    }

    private static int validateIsInteger(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static int validateNegative(int num) {
        if (num < 0) {
            throw new IllegalArgumentException();
        }

        return num;
    }

    public static LocalDateTime validateTime(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }
}
