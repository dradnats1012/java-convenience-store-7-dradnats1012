package store.model;

import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final int price;
    private final Promotion promotion;
    private final int quantity;

    public Product(String name, int price, Promotion promotion, int quantity) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
