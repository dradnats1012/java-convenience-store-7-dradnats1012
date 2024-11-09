package store.model.promotion;

import java.time.LocalDate;

import store.util.Validator;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(String startDate, String endDate){
        this.startDate = Validator.validateDate(startDate);
        this.endDate = Validator.validateDate(endDate);
    }
}
