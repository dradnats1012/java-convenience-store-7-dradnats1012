package store.model.product;

import store.model.promotion.Promotion;

public class Product {

    private final String name;
    private final Price price;
    private final Quantity quantity;
    private final Promotion promotion;

    public Product(String name, String price, String quantity, Promotion promotion) {
        this.name = name;
        this.price = new Price(price);
        this.quantity = new Quantity(quantity);
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
