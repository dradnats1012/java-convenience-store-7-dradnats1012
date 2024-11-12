package store.model.promotion;

import store.util.Validator;

public class Get {

    private final int get;

    public Get(String get) {
        this.get = Validator.validateNum(get);
    }

    public int getCount() {
        return get;
    }
}
