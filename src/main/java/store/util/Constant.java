package store.util;

public enum Constant {

    ;

    private final String constant;

    Constant(String message) {
        this.constant = message;
    }

    public String getMessage() {
        return constant;
    }
}
