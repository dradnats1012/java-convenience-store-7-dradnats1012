package store.model.membership;

public class MemberShip {

    private static final int MAX_DISCOUNT = 8000;
    private static final double DISCOUNT_RATE = 0.3;

    private final int nonePromotionMoney;
    private final int saledMoney;

    public MemberShip(int nonePromotionMoney){
        this.nonePromotionMoney = nonePromotionMoney;
        this.saledMoney = calculateDiscountedMoney();
    }

    private int calculateDiscountedMoney() {
        int calculatedDiscount = (int) (nonePromotionMoney * DISCOUNT_RATE);
        return Math.min(calculatedDiscount, MAX_DISCOUNT);
    }

    public int getSaledMoney(){
        return saledMoney;
    }
}
