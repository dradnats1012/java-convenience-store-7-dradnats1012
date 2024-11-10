package store.model.promotion;

public class Promotion {
    private final String name;
    private final Buy buy;
    private final Get get;
    private final Period period;
    private final boolean isPeriod;

    public Promotion(String name, String buy, String get, String startDate, String endDate) {
        this.name = name;
        this.buy = new Buy(buy);
        this.get = new Get(get);
        this.period = new Period(startDate, endDate);
        this.isPeriod = period.checkPeriod();
    }

    public String getName() {
        return name;
    }

    public boolean getIsPeriod() {
        return isPeriod;
    }

    public PromotionBenefitDTO purchase(int productQuantity, int purchaseQuantity) {
        int total = buy.getBuy() + get.getGet();
        int remain = purchaseQuantity % total;

        if (purchaseQuantity >= total) {
            handleFullBenefit(purchaseQuantity, total, remain);
        }
        if (purchaseQuantity >= buy.getBuy()) {
            return PromotionBenefitDTO.of(0, get.getGet());
        }

        return PromotionBenefitDTO.of(0, 0);
    }

    private PromotionBenefitDTO handleFullBenefit(int purchaseQuantity, int total, int remain) {
        int givenBenefit = (purchaseQuantity / total) * get.getGet();
        if (remain < buy.getBuy()) {
            return PromotionBenefitDTO.of(givenBenefit, 0);
        }
        return PromotionBenefitDTO.of(givenBenefit, remain - buy.getBuy() + 1);
    }

    private boolean isBenefit(int remain) {
        return remain >= buy.getBuy();
    }
}
