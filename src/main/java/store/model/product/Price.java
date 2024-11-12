package store.model.product;

import store.util.Validator;

public class Price {

    private final int price;

    public Price(String price) {
        this.price = Validator.validateNum(price);
    }

    public int getPrice() {
        return price;
    }
}
