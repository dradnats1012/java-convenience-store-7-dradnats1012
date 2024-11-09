package store.model;

import java.util.ArrayList;
import java.util.List;

import store.model.product.Product;
import store.model.promotion.PromotionManager;
import store.util.Parser;

public class Store {

    private final List<Product> products = new ArrayList<>();
    private final PromotionManager manager;

    public Store(List<String> productsFile, PromotionManager manager) {
        this.manager = manager;
        addProducts(productsFile);
    }

    private void addProducts(List<String> productFile) {
        productFile.stream()
            .skip(1)
            .map(Parser::parsedByComma)
            .forEach(product ->
                products.add(
                    new Product(
                        product.get(0).trim(),
                        product.get(1).trim(),
                        product.get(2).trim(),
                        manager.findByName(product.get(3).trim())
                    )
                )
            );
    }
}
