import java.util.ArrayList;
import java.util.List;

public class SearchFilterItemsTest {

    public static void main(String[] args) {
        SearchFilterItemsUseCase useCase = new SearchFilterItemsUseCase();
        List<Item> itemDatabase = createTestDatabase();

        testValidSearchTextOnly(useCase, itemDatabase);
        testValidSearchAndCategory(useCase, itemDatabase);
        testValidSearchAndPriceRange(useCase, itemDatabase);
        testValidSearchAndSortOption(useCase, itemDatabase);
        testAllInputsValid(useCase, itemDatabase);
        testNoResultsFound(useCase, itemDatabase);
        testInvalidSearchText(useCase, itemDatabase);
        testInvalidCategory(useCase, itemDatabase);
        testInvalidPriceRange(useCase, itemDatabase);
        testDatabaseFailure(useCase);

        System.out.println("All test cases passed.");
    }

    private static List<Item> createTestDatabase() {
        List<Item> items = new ArrayList<>();

        items.add(new Item("Laptop", "Electronics", 500.00, "Good", "UTD", 3000));
        items.add(new Item("Gaming Laptop", "Electronics", 800.00, "Like New", "Dallas", 2000));
        items.add(new Item("Desk Chair", "Furniture", 40.00, "Like New", "Dallas", 2500));
        items.add(new Item("Calculus Book", "Books", 25.00, "Fair", "Richardson", 1500));
        items.add(new Item("Book Shelf", "Furniture", 60.00, "Good", "UTD", 1000));

        return items;
    }

    private static void testValidSearchTextOnly(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Laptop", null, null, null, null, null, null, itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SUCCESS;
        assert result.getItems().size() == 2;
        assert result.getItems().get(0).getTitle().contains("Laptop")
                || result.getItems().get(1).getTitle().contains("Laptop");
    }

    private static void testValidSearchAndCategory(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Chair", "Furniture", null, null, null, null, null, itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SUCCESS;
        assert result.getItems().size() == 1;
        assert result.getItems().get(0).getTitle().equals("Desk Chair");
    }

    private static void testValidSearchAndPriceRange(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Book", null, 10.00, 30.00, null, null, null, itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SUCCESS;
        assert result.getItems().size() == 1;
        assert result.getItems().get(0).getTitle().equals("Calculus Book");
    }

    private static void testValidSearchAndSortOption(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Laptop", null, null, null, null, null, "PriceLowToHigh", itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SUCCESS;
        assert result.getItems().size() == 2;
        assert result.getItems().get(0).getTitle().equals("Laptop");
        assert result.getItems().get(1).getTitle().equals("Gaming Laptop");
        assert result.getItems().get(0).getPrice() <= result.getItems().get(1).getPrice();
    }

    private static void testAllInputsValid(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Laptop", "Electronics", 400.00, 600.00, "Good", "UTD", "Newest", itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SUCCESS;
        assert result.getItems().size() == 1;
        assert result.getItems().get(0).getTitle().equals("Laptop");
    }

    private static void testNoResultsFound(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Phone", "Electronics", 100.00, 200.00, "New", "UTD", "Newest", itemDatabase
        );

        assert result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.NO_RESULTS;
        assert result.getItems().isEmpty();
        assert result.getMessage().equals("No matching items found.");
    }

    private static void testInvalidSearchText(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "", null, null, null, null, null, null, itemDatabase
        );

        assert !result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.INVALID_QUERY;
        assert result.getMessage().equals("Error: Invalid search text. Must be 1–100 characters.");
    }

    private static void testInvalidCategory(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Laptop", "Vehicles", null, null, null, null, null, itemDatabase
        );

        assert !result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.INVALID_CATEGORY;
        assert result.getMessage().equals("Error: Invalid category 'Vehicles'.");
    }

    private static void testInvalidPriceRange(SearchFilterItemsUseCase useCase, List<Item> itemDatabase) {
        SearchResult result = useCase.searchItems(
                "Laptop", null, 500.00, 100.00, null, null, null, itemDatabase
        );

        assert !result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.INVALID_PRICE;
        assert result.getMessage().equals("Error: Invalid price range. MinPrice=500.0 MaxPrice=100.0");
    }

    private static void testDatabaseFailure(SearchFilterItemsUseCase useCase) {
        SearchResult result = useCase.searchItems(
                "Laptop", null, null, null, null, null, null, null
        );

        assert !result.isSuccess();
        assert result.getResultType() == SearchResult.ResultType.SYSTEM_ERROR;
        assert result.getMessage().equals("System error: Service unavailable. Please try again later.");
    }
}
