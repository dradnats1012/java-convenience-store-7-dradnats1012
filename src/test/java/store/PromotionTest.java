package store;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import store.model.promotion.TwoPlusOnePromotion;

public class PromotionTest {

    @Test
    void 프로모션_buy_숫자가_아니면_예외() {
        assertThatThrownBy(() -> new TwoPlusOnePromotion("2+1", "이", "1", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_get_숫자가_아니면_예외() {
        assertThatThrownBy(() -> new TwoPlusOnePromotion("2+1", "2", "일", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_Date_형식이_아니면_예외() {
        assertThatThrownBy(() -> new TwoPlusOnePromotion("2+1", "2", "1", "2024-01-0111", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
