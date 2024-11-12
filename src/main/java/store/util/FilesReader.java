package store.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import store.view.OutputView;

public class FilesReader {

    private FilesReader() {
    }

    public static List<String> readProductsFile() {
        List<String> products = new ArrayList<>();
        try {
            Path path = Path.of("src/main/resources/products.md");
            products = Files.readAllLines(path);
        } catch (IOException e) {
            OutputView.printException(e.getMessage());
        }
        return products;
    }

    public static List<String> readPromotionFile() {
        List<String> promotions = new ArrayList<>();
        try {
            Path path = Path.of("src/main/resources/promotions.md");
            promotions = Files.readAllLines(path);
        } catch (IOException e) {
            OutputView.printException(e.getMessage());
        }
        return promotions;
    }
}