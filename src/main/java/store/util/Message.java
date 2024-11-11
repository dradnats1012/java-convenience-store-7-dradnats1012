package store.util;

public enum Message {

    INPUT_PURCHASE_LIST("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    INPUT_IS_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
    INPUT_IS_MORE_PURCHASE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    INPUT_NO_PROMOTION("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"),
    INPUT_MORE_BENEFIT("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"),
    HELLO_MESSAGE("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n"),
    RECEIPT_HEADER("==============W 편의점================"),
    RECEIPT_LIST_NAME("상품명\t\t\t\t수량\t\t\t금액"),
    RECEIPT_LIST("%-15s %5s %10s%n"),
    RECEIPT_BENEFIT("=============증\t\t정==============="),
    RECEIPT_BENEFIT_LIST("%s\t\t\t\t%s%n"),
    RECEIPT_LINE("===================================="),
    RECEIPT_TOTAL_MONEY("총구매액\t\t\t\t%s\t\t%s%n"),
    RECEIPT_PROMOTION_SALE("행사할인\t\t\t\t\t\t-%s%n"),
    RECEIPT_MEMBERSHIP_SALE("멤버십할인\t\t\t\t\t\t-%s%n"),
    RECEIPT_PAY_MONEY("내실돈\t\t\t\t\t\t %s%n%n");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
