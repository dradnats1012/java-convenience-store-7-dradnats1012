package store.model.promotion;

public class Promotion {
    private String name;
    private Buy buy;
    private Get get;
    private Period period;

    public Promotion(String name, String buy, String get, String startDate, String endDate) {
        this.name = name;
        this.buy = new Buy(buy);
        this.get = new Get(get);
        this.period = new Period(startDate, endDate);
    }

    public String getName(){
        return name;
    }

}
