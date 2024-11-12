package store.model.store;

import static store.util.Constant.NONE_QUANTITY;
import static store.util.Constant.PRODUCT_COUNT;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import store.model.product.Product;

public record StoreDTO(
    List<ProductDTO> products
) {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    public static StoreDTO from(Store store) {
        return new StoreDTO(
            store.getProducts().stream()
                .flatMap(product -> ProductDTO.of(product).stream())
                .collect(Collectors.toList())
        );
    }

    public record ProductDTO(
        String name,
        String price,
        String quantity,
        String promotion
    ) {
        public static List<ProductDTO> of(Product product) {
            List<ProductDTO> productDTOs = new ArrayList<>();

            productDTOs.addAll(hasPromotionQuantity(product));
            productDTOs.addAll(noPromotionQuantity(product));
            productDTOs.addAll(hasNormalQuantity(product));
            productDTOs.addAll(noNormalQuantity(product));

            return productDTOs;
        }

        private static List<ProductDTO> hasPromotionQuantity(Product product) {
            List<ProductDTO> productDTOs = new ArrayList<>();
            if (product.getPromotion() != null && product.getPromotionQuantity() > 0) {
                productDTOs.add(new ProductDTO(
                    product.getName(),
                    DECIMAL_FORMAT.format(product.getPrice()),
                    product.getPromotionQuantity() + PRODUCT_COUNT.getMessage(),
                    product.getPromotion().getName()));
            }
            return productDTOs;
        }

        private static List<ProductDTO> noPromotionQuantity(Product product) {
            List<ProductDTO> productDTOs = new ArrayList<>();
            if (product.getPromotion() != null && product.getPromotionQuantity() == 0) {
                productDTOs.add(new ProductDTO(
                    product.getName(),
                    DECIMAL_FORMAT.format(product.getPrice()),
                    NONE_QUANTITY.getMessage(),
                    product.getPromotion().getName()));
            }
            return productDTOs;
        }

        private static List<ProductDTO> hasNormalQuantity(Product product) {
            List<ProductDTO> productDTOs = new ArrayList<>();
            if (product.getNormalQuantity() > 0) {
                productDTOs.add(new ProductDTO(
                    product.getName(),
                    DECIMAL_FORMAT.format(product.getPrice()),
                    product.getNormalQuantity() + PRODUCT_COUNT.getMessage(),
                    ""));
            }
            return productDTOs;
        }

        private static List<ProductDTO> noNormalQuantity(Product product) {
            List<ProductDTO> productDTOs = new ArrayList<>();
            if (product.getNormalQuantity() == 0) {
                productDTOs.add(new ProductDTO(
                    product.getName(),
                    DECIMAL_FORMAT.format(product.getPrice()),
                    NONE_QUANTITY.getMessage(),
                    ""));
            }
            return productDTOs;
        }
    }
}