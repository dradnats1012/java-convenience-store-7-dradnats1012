package store.view;

import store.model.store.StoreDTO;

public class OutputView {

    private OutputView(){}

    public static void printReceipt(StoreDTO storeDTO){
        System.out.println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n");

        storeDTO.products().forEach(productDTO ->
            System.out.printf("- %s %s원 %s %s%n",
                productDTO.name(),
                productDTO.price(),
                productDTO.quantity(),
                productDTO.promotion()
            )
        );
    }
}
