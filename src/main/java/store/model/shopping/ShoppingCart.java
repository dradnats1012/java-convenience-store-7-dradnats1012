package store.model.shopping;

import java.util.Map;
import java.util.stream.Collectors;

import store.util.Validator;

public class ShoppingCart {

    private final Map<String, String> purchases;
    private final Map<String, Integer> shoppingCart;

    public ShoppingCart(Map<String, String> purchaseList) {
        this.purchases = purchaseList;
        this.shoppingCart = convertToIntegerMap(purchases);
    }

    private Map<String, Integer> convertToIntegerMap(Map<String, String> purchaseList) {
        return purchaseList.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    Validator.validateNum(entry.getValue().trim());
                    return Integer.parseInt(entry.getValue().trim());
                }
            ));
    }

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public int getTotalQuantity() {
        return shoppingCart.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public Map<String, String> getPurchases() {
        return purchases;
    }
}
