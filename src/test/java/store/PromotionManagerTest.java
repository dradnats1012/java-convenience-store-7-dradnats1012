package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;

import store.model.promotion.PromotionManager;

public class PromotionManagerTest {

    @Test
    void 프로모션_매니저_입력_확인() {
        assertSimpleTest(() -> {
            PromotionManager manager = new PromotionManager(List.of(
                "name,buy,get,start_date,end_date",
                "탄산2+1,2,1,2024-01-01,2024-12-31",
                "MD추천상품,1,1,2024-01-01,2024-12-31",
                "반짝할인,1,1,2024-11-01,2024-11-30"));
            assertThat(manager.getSize()).isEqualTo(3);
        });
    }
}