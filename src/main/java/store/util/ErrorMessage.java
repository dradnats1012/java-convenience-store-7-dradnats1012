package store.util;

public enum ErrorMessage {

    ERROR_INCORRECT_PRODUCT_QUANTITY("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    ERROR_EXCEED_QUANTITY("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ERROR_NOT_EXIST_PRODUCT("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    ERROR_INCORRECT_INPUT("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
