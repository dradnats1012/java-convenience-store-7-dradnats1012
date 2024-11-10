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

    public int getPromotionQuantity(){
        return promotionQuantity.getQuantity();
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void purchasedByPromotion(int purchaseQuantity){
        PromotionBenefitDTO promotionBenefitDTO = promotion.purchase(promotionQuantity.getQuantity(), purchaseQuantity);
        //int purchasedAmount = promotionBenefitDTO.purchasedAmount();
        minusQuantity(purchaseQuantity);

        int remainAmount = promotionBenefitDTO.remainBenefit();

        if(remainAmount != 0){
            // 무료로 더 받을 수 있습니다
        }
    }

    public void purchasedByNormal(int purchaseQuantity){
        if(purchaseQuantity > normalQuantity.getQuantity()){
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PRODUCT.getMessage());
        }

        normalQuantity.minusQuantity(purchaseQuantity);
    }

    private void minusQuantity(int purchaseQuantity){
        if(purchaseQuantity > promotionQuantity.getQuantity() + normalQuantity.getQuantity()){
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PRODUCT.getMessage());
        }

        if(purchaseQuantity > promotionQuantity.getQuantity()){
            promotionQuantity.minusQuantity(promotionQuantity.getQuantity());
            int remain = purchaseQuantity - promotionQuantity.getQuantity();
            normalQuantity.minusQuantity(remain);
        }
    }
}
