package store.model.store;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import store.model.product.Product;

public record StoreDTO(
    List<ProductDTO> products
) {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    public static StoreDTO from (Store store){
        return new StoreDTO(
            store.getProducts().stream()
                .map(ProductDTO::of)
                .collect(Collectors.toList())
        );
    }

    public record ProductDTO(
        String name,
        String price,
        String quantity,
        String promotion
    ){
        public static ProductDTO of(Product product){

            return new ProductDTO(
                product.getName(),
                DECIMAL_FORMAT.format(product.getPrice()),
                product.getQuantity() == 0 ? "재고 없음" : product.getQuantity() + "개",
                product.getPromotion() != null ? product.getPromotion().getName() : ""
            );
        }
    }
}
