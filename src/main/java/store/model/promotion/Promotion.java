package store.model.promotion;

import java.time.LocalDateTime;

import store.util.Validator;

public abstract class Promotion {
    protected String name;
    protected Buy buy;
    protected Get get;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    protected Promotion(String name, String buy, String get, String startDate, String endDate) {
        this.name = name;
        this.buy = new Buy(buy);
        this.get = new Get(get);
        this.startDate = Validator.validateTime(startDate);
        this.endDate = Validator.validateTime(endDate);
    }
}
