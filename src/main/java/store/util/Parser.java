package store.util;

import static store.util.ErrorMessage.ERROR_INCORRECT_PRODUCT_QUANTITY;

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
        String purchaseRegex = purchased.replaceAll("[\\[\\]]", "");

        return List.of(purchaseRegex.split(",")).stream()
            .map(purchase -> {
                String[] parts = purchase.split("-");
                if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
                    throw new IllegalArgumentException(ERROR_INCORRECT_PRODUCT_QUANTITY.getMessage());
                }
                return parts;
            })
            .collect(Collectors.toMap(
                product -> product[0].trim(),
                product -> product[1].trim()
            ));
    }
}
