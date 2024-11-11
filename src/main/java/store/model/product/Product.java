package store.model.product;

import static store.util.ErrorMessage.ERROR_NOT_EXIST_PRODUCT;

import store.model.promotion.Promotion;
import store.model.promotion.PromotionBenefitDTO;

public class Product {

    private final String name;
    private final Price price;
    private final Quantity normalQuantity;
    private final Quantity promotionQuantity;
    private final Promotion promotion;

    public Product(String name, String price, String normalQuantity, String promotionQuantity, Promotion promotion) {
        this.name = name;
        this.price = new Price(price);
        this.normalQuantity = new Quantity(normalQuantity);
        this.promotionQuantity = new Quantity(promotionQuantity);
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getNormalQuantity() {
        return normalQuantity.getQuantity();
    }

    public int getPromotionQuantity() {
        return promotionQuantity.getQuantity();
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public PromotionBenefitDTO purchasedByPromotion(int purchaseQuantity) {
        PromotionBenefitDTO promotionBenefitDTO = promotion.purchase(purchaseQuantity);
        minusPromotionQuantity(purchaseQuantity);

        return promotionBenefitDTO;
    }

    public void purchasedByNormal(int purchaseQuantity) {
        if (purchaseQuantity > normalQuantity.getQuantity()) {
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PRODUCT.getMessage());
        }
        normalQuantity.minus(purchaseQuantity);
    }

    private void minusPromotionQuantity(int purchaseQuantity) {
        validateQuantity(purchaseQuantity);

        if (purchaseQuantity <= promotionQuantity.getQuantity()) {
            promotionQuantity.minus(purchaseQuantity);
            return;
        }

        int remainingPurchase = purchaseQuantity - promotionQuantity.getQuantity();
        promotionQuantity.minus(promotionQuantity.getQuantity());
        normalQuantity.minus(remainingPurchase);
    }

    private void validateQuantity(int purchaseQuantity) {
        int totalAvailableQuantity = promotionQuantity.getQuantity() + normalQuantity.getQuantity();
        if (purchaseQuantity > totalAvailableQuantity) {
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PRODUCT.getMessage());
        }
    }

    public void updateNormalQuantity(int quantity) {
        this.normalQuantity.add(quantity); // 기존 수량에 추가
    }

    public void updatePromotionQuantity(int quantity) {
        this.promotionQuantity.add(quantity); // 기존 수량에 추가
    }
}
