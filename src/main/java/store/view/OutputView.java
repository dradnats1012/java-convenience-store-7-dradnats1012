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

        receipt.append(RECEIPT_HEADER.getMessage()).append("\n");
        receipt.append(RECEIPT_LIST_NAME.getMessage()).append("\n");

        receiptDTO.productDTOS().forEach(product ->
            receipt.append(
                String.format(RECEIPT_LIST.getMessage(), product.name(), product.quantity(), product.price()))
        );

        receipt.append(RECEIPT_BENEFIT.getMessage()).append("\n");
        receiptDTO.benefitDTOS().forEach(benefit ->
            receipt.append(String.format(RECEIPT_BENEFIT_LIST.getMessage(), benefit.name(), benefit.quantity()))
        );

        receipt.append(RECEIPT_LINE.getMessage()).append("\n");
        ReceiptDTO.MoneyDTO money = receiptDTO.moneyDTO();
        receipt.append(String.format(RECEIPT_TOTAL_MONEY.getMessage(), receiptDTO.totalQuantity(), money.totalMoney()));
        receipt.append(String.format(RECEIPT_PROMOTION_SALE.getMessage(), money.promotionSaleMoney()));
        receipt.append(String.format(RECEIPT_MEMBERSHIP_SALE.getMessage(), money.membershipSaleMoney()));
        receipt.append(String.format(RECEIPT_PAY_MONEY.getMessage(), money.payMoney()));

        System.out.println(receipt);
    }

    public static void printException(String errorMessage) {
        System.out.println(errorMessage);
    }
}

