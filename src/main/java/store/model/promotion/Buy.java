package store.model.promotion;

import store.util.Validator;

public class Buy {

    private final int buy;

    public Buy(String buy){
        this.buy = Validator.validateNum(buy);
    }

    public int getBuy(){
        return buy;
    }
}
