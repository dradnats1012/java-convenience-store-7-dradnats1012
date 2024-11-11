package store.model.store;

import static store.util.ErrorMessage.ERROR_EXCEED_QUANTITY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import store.model.product.Product;
import store.model.promotion.Promotion;
import store.model.promotion.PromotionBenefitDTO;
import store.model.promotion.PromotionManager;
import store.util.Parser;

public class Store {

    private final List<Product> products = new ArrayList<>();
    private final PromotionManager manager;
    private int givenBenefitMoney = 0;
    private int promotionBenefitMoney = 0;
    private Map<String, String> benefits = new HashMap<>();

    public Store(List<String> productsFile, PromotionManager manager) {
        this.manager = manager;
        addProducts(productsFile);
    }

    private void addProducts(List<String> productFile) {
        productFile.stream()
            .skip(1)
            .map(Parser::parsedByComma)
            .forEach(this::addProduct);
    }

    private void addProduct(List<String> productData) {
        String name = productData.get(0).trim();
        String price = productData.get(1).trim();
        String quantity = productData.get(2).trim();
        String promotionName = productData.get(3).trim();

        Promotion promotion = manager.findByName(promotionName);
        Product existingProduct = findByName(name);
        if (alreadyExist(existingProduct, promotion, quantity))
            return;

        String normalQuantity = promotion == null ? quantity : "0";
        String promotionQuantity = promotion != null ? quantity : "0";

        products.add(new Product(name, price, normalQuantity, promotionQuantity, promotion));
    }

    private static boolean alreadyExist(Product existingProduct, Promotion promotion, String quantity) {
        if (existingProduct != null) {
            if (promotion == null) {
                existingProduct.updateNormalQuantity(Integer.parseInt(quantity));
            }

            if (promotion != null) {
                existingProduct.updatePromotionQuantity(Integer.parseInt(quantity));
            }
            return true;
        }
        return false;
    }

    public void purchaseProduct(String productName, int purchaseQuantity) {
        for (Product product : products) {
            if(purchaseQuantity > product.getPromotionQuantity() + product.getNormalQuantity()){
                throw new IllegalArgumentException(ERROR_EXCEED_QUANTITY.getMessage());
            }
            if (product.getName().equals(productName)) {
                Promotion promotion = product.getPromotion();
                if (promotion != null && promotion.getIsPeriod()) {
                    PromotionBenefitDTO promotionBenefitDTO = product.purchasedByPromotion(purchaseQuantity);
                    calculateGivenBenefitMoney(productName, promotionBenefitDTO.givenBenefit());
                    calculatePromotionBenefitMoney(productName, promotionBenefitDTO.givenBenefit(), promotion);
                }
                if (promotion == null || !promotion.getIsPeriod()) {
                    product.purchasedByNormal(purchaseQuantity);
                }
            }
        }
    }

    public int calculateTotalMoney(String productName, int purchaseQuantity) {
        return findByName(productName).getPrice() * purchaseQuantity;
    }

    public void calculateGivenBenefitMoney(String productName, int givenBenefit) {
        givenBenefitMoney += findByName(productName).getPrice() * givenBenefit;
        benefits.put(productName, String.valueOf(givenBenefit));
    }

    public void calculatePromotionBenefitMoney(String productName, int givenBenefit, Promotion promotion) {
        promotionBenefitMoney +=
            findByName(productName).getPrice() * (givenBenefit / promotion.getCount()) * promotion.getTotal();
    }

    public Product findByName(String productName) {
        return products.stream()
            .filter(p -> p.getName().equals(productName))
            .findFirst().orElse(null);
    }

    public int getGivenBenefitMoney() {
        return givenBenefitMoney;
    }

    public int getPromotionBenefitMoney() {
        return promotionBenefitMoney;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Map<String, String> getBenefits() {
        return benefits;
    }
}