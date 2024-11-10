package store.model.store;

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
            .forEach(this::addProduct);

        addProductsNullPromotion();

        /*products.stream()
            .forEach(product -> System.out.println(
                product.getName() + " " + product.getQuantity() + " " + product.getPromotion()));
    */}

    private void addProduct(List<String> product) {
        products.add(
            new Product(
                product.get(0).trim(),
                product.get(1).trim(),
                product.get(2).trim(),
                manager.findByName(product.get(3).trim())
            )
        );
    }

    private void addProductsNullPromotion() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            if (product.getPromotion() != null) {
                boolean existsWithNullPromotion = products.stream()
                    .anyMatch(p -> p.getName().equals(product.getName()) && p.getPromotion() == null);

                if (!existsWithNullPromotion) {
                    Product newProduct = new Product(
                        product.getName(),
                        String.valueOf(product.getPrice()),
                        "0",
                        null
                    );

                    products.add(i + 1, newProduct);
                    i++;
                }
            }
        }
    }

    public List<Product> getProducts(){
        return products;
    }
}
