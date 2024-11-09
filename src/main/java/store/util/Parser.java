package store.util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {

    public static List<String> parsedByComma(String inventory) {
        return Optional.ofNullable(inventory)
            .filter(s -> !s.trim().isEmpty())
            .map(s -> List.of(s.split(",")))
            .orElse(List.of());
    }

    public static Map<String, String> parsedPurchase(String purchased) {
        return List.of(purchased.split(",")).stream()
            .map(purchase -> purchase.split("-"))
            .collect(Collectors.toMap(
                product -> product[0].trim(),
                product -> product[1].trim()
            ));
    }
}
