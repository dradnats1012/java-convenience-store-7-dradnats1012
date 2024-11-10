package store.model.shopping;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import store.model.membership.MemberShip;
import store.model.store.Counter;

public record ReceiptDTO(
    List<ProductDTO> productDTOS,
    List<BenefitDTO> benefitDTOS,
    MoneyDTO moneyDTO,
    int totalQuantity
) {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    public static ReceiptDTO of(Map<String, String> purchases,
        Map<String, String> benefits, Counter counter, MemberShip memberShip) {
        return new ReceiptDTO(
            purchases.entrySet().stream()
                .map(entry -> ProductDTO.of(entry, counter))
                .collect(Collectors.toList()),
            benefits.entrySet().stream()
                .map(BenefitDTO::of)
                .collect(Collectors.toList()),
            MoneyDTO.of(counter, memberShip),
            counter.getTotalQuantity()
        );
    }

    public record ProductDTO(
        String name,
        String quantity,
        String price
    ) {
        public static ProductDTO of(Map.Entry<String, String> entry, Counter counter) {
            String name = entry.getKey();
            String quantity = entry.getValue();
            String price = DECIMAL_FORMAT.format(counter.getTotalMoney());
            return new ProductDTO(name, quantity, price);
        }
    }

    public record BenefitDTO(
        String name,
        String quantity
    ) {
        public static BenefitDTO of(Map.Entry<String, String> entry) {
            return new BenefitDTO(entry.getKey(), entry.getValue());
        }
    }

    public record MoneyDTO(
        String totalMoney,
        String promotionSaleMoney,
        String membershipSaleMoney,
        String payMoney
    ) {
        public static MoneyDTO of(Counter counter, MemberShip memberShip) {
            String totalMoney = DECIMAL_FORMAT.format(counter.getTotalMoney());
            String promotionSaleMoney = DECIMAL_FORMAT.format(counter.getBenefitMoney());
            String membershipSaleMoney = DECIMAL_FORMAT.format(memberShip.getSaledMoney());
            String payMoney = DECIMAL_FORMAT.format(
                counter.getTotalMoney() - counter.getBenefitMoney() - memberShip.getSaledMoney()
            );
            return new MoneyDTO(totalMoney, promotionSaleMoney, membershipSaleMoney, payMoney);
        }
    }
}