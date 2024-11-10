package store.model.product;

import store.util.Validator;

public class Quantity {

    private int quantity;

    public Quantity(String quantity){
        this.quantity = Validator.validateNum(quantity);
    }

    public int getQuantity(){
        return quantity;
    }

    public void minus(int purchasedAmount){
        quantity -= purchasedAmount;
    }

    public void add(int amount){
        quantity += amount;
    }
}