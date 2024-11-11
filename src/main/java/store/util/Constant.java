package store.util;

public enum Constant {
    NONE_QUANTITY("재고 없음"),
    PRICE_DECIMAL_FORMAT("###,###"),
    PRODUCT_COUNT("개"),
    YES("Y"),
    NO("N"),
    BAR("-"),
    COMMA(","),
    ;

    private final String constant;

    Constant(String message) {
        this.constant = message;
    }

    public String getMessage() {
        return constant;
    }
}
