package store;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductTest {

    @Test
    void 프로덕트_price_Integer_아니면_예외() {
        Promotion promotion = new Promotion("탄산2+1", "2", "1", "2024-01-01", "2024-12-31");

        assertThatThrownBy(() -> new Product("콜라", "1000원", "10", "10", promotion))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로덕트_quantity_Integer_아니면_예외() {
        Promotion promotion = new Promotion("탄산2+1", "2", "1", "2024-01-01", "2024-12-31");

        assertThatThrownBy(() -> new Product("콜라", "1000", "10개", "10", promotion))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로덕트_price_음수면_예외() {
        Promotion promotion = new Promotion("탄산2+1", "2", "1", "2024-01-01", "2024-12-31");

        assertThatThrownBy(() -> new Product("콜라", "-1000", "10", "10", promotion))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로덕트_quantity_음수면_예외() {
        Promotion promotion = new Promotion("탄산2+1", "2", "1", "2024-01-01", "2024-12-31");

        assertThatThrownBy(() -> new Product("콜라", "1000", "-10", "10", promotion))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
