package store.model.promotion;

import java.util.ArrayList;
import java.util.List;

import store.util.Parser;

public class PromotionManager {

    private final List<Promotion> promotions = new ArrayList<>();

    public PromotionManager(List<String> promotionFile) {
        addPromotion(promotionFile);
    }

    private void addPromotion(List<String> promotionFile) {
        promotionFile.stream()
            .skip(1)
            .map(Parser::parsedByComma)
            .forEach(promotion ->
                promotions.add(
                    new Promotion(
                        promotion.get(0).trim(),
                        promotion.get(1).trim(),
                        promotion.get(2).trim(),
                        promotion.get(3).trim(),
                        promotion.get(4).trim()
                    )
                )
            );
    }

    public int getSize(){
        return promotions.size();
    }
}
