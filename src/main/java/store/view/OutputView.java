package store.view;

import static store.util.Message.*;

import store.model.shopping.ReceiptDTO;
import store.model.store.StoreDTO;
import store.util.Message;

public class OutputView {

    private OutputView() {
    }

    public static void printMenu(StoreDTO storeDTO) {
        System.out.println(Message.HELLO_MESSAGE.getMessage());

        storeDTO.products().forEach(productDTO ->
            System.out.printf("- %s %s원 %s %s%n",
                productDTO.name(),
                productDTO.price(),
                productDTO.quantity(),
                productDTO.promotion()
            )
        );
        System.out.println();
    }

    public static void printReceipt(ReceiptDTO receiptDTO) {
        System.out.println(RECEIPT_HEADER.getMessage());
        System.out.println(RECEIPT_LIST_NAME.getMessage());
        receiptDTO.productDTOS().forEach(product ->
            System.out.printf(RECEIPT_LIST.getMessage(), product.name(), product.quantity(), product.price())
        );

        // BenefitDTO 리스트 출력
        System.out.println(RECEIPT_BENEFIT.getMessage());
        receiptDTO.benefitDTOS().forEach(benefit ->
            System.out.printf(RECEIPT_BENEFIT_LIST.getMessage(), benefit.name(), benefit.quantity())
        );

        // MoneyDTO 출력
        System.out.println(RECEIPT_LINE.getMessage());
        ReceiptDTO.MoneyDTO money = receiptDTO.moneyDTO();
        System.out.printf(RECEIPT_TOTAL_MONEY.getMessage(), receiptDTO.totalQuantity(), money.totalMoney());
        System.out.printf(RECEIPT_PROMOTION_SALE.getMessage(), money.promotionSaleMoney());
        System.out.printf(RECEIPT_MEMBERSHIP_SALE.getMessage(), money.membershipSaleMoney());
        System.out.printf(RECEIPT_PAY_MONEY.getMessage(), money.payMoney());
    }
}

