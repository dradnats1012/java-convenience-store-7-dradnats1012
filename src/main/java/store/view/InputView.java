package store.view;

import static store.util.Message.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
    }

    public static String getPurchaseList() {
        System.out.println(INPUT_PURCHASE_LIST.getMessage());
        return Console.readLine();
    }

    public static String getIsMembership() {
        System.out.println(INPUT_IS_MEMBERSHIP.getMessage());
        return Console.readLine();
    }

    public static String getIsMorePurchase() {
        System.out.printf(INPUT_IS_MORE_PURCHASE.getMessage());
        return Console.readLine();
    }

    public static String getNoPromotion(String product, int count) {
        System.out.printf(INPUT_NO_PROMOTION.getMessage(), product, count + "\n");
        return Console.readLine();
    }

    public static String getMorePromotion(String product, int count) {
        System.out.printf(INPUT_MORE_BENEFIT.getMessage(), product, count + "\n");
        return Console.readLine();
    }
}
