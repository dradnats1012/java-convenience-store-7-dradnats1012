package store.model.promotion;

public record PromotionBenefitDTO(
    int givenBenefit,
    int remainBenefit
) {

    public static PromotionBenefitDTO of(int givenBenefit, int remainBenefit) {
        return new PromotionBenefitDTO(
            givenBenefit,
            remainBenefit
        );
    }
}
