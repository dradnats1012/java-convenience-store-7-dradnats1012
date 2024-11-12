package store.model.promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;

import camp.nextstep.edu.missionutils.DateTimes;
import store.util.Validator;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(String startDate, String endDate) {
        this.startDate = Validator.validateDate(startDate);
        this.endDate = Validator.validateDate(endDate);
    }

    public boolean checkPeriod() {
        LocalDateTime today = DateTimes.now();

        return !today.isBefore(startDate.atStartOfDay()) && !today.isAfter(endDate.atStartOfDay());
    }
}
