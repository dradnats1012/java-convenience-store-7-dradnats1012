package store.model.store;

import store.model.shopping.ShoppingCart;

public class Counter {
    private final Store store;
    private final ShoppingCart shoppingCart;
    private final int totalMoney;

    public Counter(Store store, ShoppingCart shoppingCart) {
        this.store = store;
        this.shoppingCart = shoppingCart;
        totalMoney = calculateTotalMoney();
    }

    public void purchaseProducts() {
        shoppingCart.getShoppingCart().forEach(store::purchaseProduct);
    }

    private int calculateTotalMoney() {
        return shoppingCart.getShoppingCart().entrySet().stream()
            .mapToInt(entry -> store.calculateTotalMoney(entry.getKey(), entry.getValue()))
            .sum();
    }

    public int getBenefitMoney() {
        return store.getGivenBenefitMoney();
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getNonePromotionMoney(){
        return totalMoney - store.getPromotionBenefitMoney();
    }

    public int getTotalQuantity(){
        return shoppingCart.getTotalQuantity();
    }
}
