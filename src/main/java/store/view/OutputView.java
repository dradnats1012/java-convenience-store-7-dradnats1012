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
        StringBuilder receipt = new StringBuilder();

        // 헤더 및 리스트 헤더 추가
        receipt.append(RECEIPT_HEADER.getMessage()).append("\n");
        receipt.append(RECEIPT_LIST_NAME.getMessage()).append("\n");

        // 상품 목록 추가
        receiptDTO.productDTOS().forEach(product ->
            receipt.append(
                    String.format(RECEIPT_LIST.getMessage(), product.name(), product.quantity(), product.price()))
        );

        // 증정 혜택 섹션 추가
        receipt.append(RECEIPT_BENEFIT.getMessage()).append("\n");
        receiptDTO.benefitDTOS().forEach(benefit ->
            receipt.append(String.format(RECEIPT_BENEFIT_LIST.getMessage(), benefit.name(), benefit.quantity()))
        );

        // 구분선 및 금액 섹션 추가
        receipt.append(RECEIPT_LINE.getMessage()).append("\n");
        ReceiptDTO.MoneyDTO money = receiptDTO.moneyDTO();
        receipt.append(String.format(RECEIPT_TOTAL_MONEY.getMessage(), receiptDTO.totalQuantity(), money.totalMoney()));
        receipt.append(String.format(RECEIPT_PROMOTION_SALE.getMessage(), money.promotionSaleMoney()));
        receipt.append(String.format(RECEIPT_MEMBERSHIP_SALE.getMessage(), money.membershipSaleMoney()));
        receipt.append(String.format(RECEIPT_PAY_MONEY.getMessage(), money.payMoney()));

        // 최종 출력
        System.out.println(receipt.toString());
    }

    public static void printException(String errorMessage) {
        System.out.println(errorMessage);
    }
}

