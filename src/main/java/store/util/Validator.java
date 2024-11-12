package store.util;

import static store.util.Constant.NO;
import static store.util.Constant.YES;
import static store.util.ErrorMessage.ERROR_INCORRECT_INPUT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {

    private Validator() {
    }

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

    public static LocalDate validateDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateYesOrNo(String userConsent) {
        if (!userConsent.equalsIgnoreCase(YES.getMessage()) && !userConsent.equalsIgnoreCase(NO.getMessage())) {
            throw new IllegalArgumentException(ERROR_INCORRECT_INPUT.getMessage());
        }
    }
}
