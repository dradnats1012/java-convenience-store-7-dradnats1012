package store.model.product;

import store.util.Validator;

public class Quantity {

    private final int quantity;

    public Quantity(String quantity){
        this.quantity = Validator.validateNum(quantity);
    }

    public int getQuantity(){
        return quantity;
    }
}
