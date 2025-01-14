package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import store.model.promotion.Promotion;

public class PromotionTest {

    @Test
    void 프로모션_buy_숫자가_아니면_예외() {
        assertThatThrownBy(() -> new Promotion("2+1", "이", "1", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_get_숫자가_아니면_예외() {
        assertThatThrownBy(() -> new Promotion("2+1", "2", "일", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_buy_음수면_예외() {
        assertThatThrownBy(() -> new Promotion("2+1", "-2", "1", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_get_음수면_예외() {
        assertThatThrownBy(() -> new Promotion("2+1", "2", "-1", "2024-01-01", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_Date_형식이_아니면_예외() {
        assertThatThrownBy(() -> new Promotion("2+1", "2", "1", "2024-01-0111", "2024-12-31"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 프로모션_기간_맞는지_확인() {
        assertSimpleTest(() -> {
            Promotion promotion = new Promotion("2+1", "2", "1", "2024-01-01", "2024-12-31");
            assertThat(promotion.getIsPeriod()).isEqualTo(true);
        });
    }

    @Test
    void 프로모션_기간_아닌지_확인() {
        assertSimpleTest(() -> {
            Promotion promotion = new Promotion("2+1", "2", "1", "2023-01-01", "2023-12-31");
            assertThat(promotion.getIsPeriod()).isEqualTo(false);
        });
    }
}
