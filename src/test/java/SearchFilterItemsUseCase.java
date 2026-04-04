import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SearchFilterItemsUseCase {

    private static final List<String> VALID_CATEGORIES = Arrays.asList(
            "Electronics", "Furniture", "Books"
    );

    private static final List<String> VALID_CONDITIONS = Arrays.asList(
            "New", "Like New", "Good", "Fair"
    );

    private static final List<String> VALID_LOCATIONS = Arrays.asList(
            "UTD", "Dallas", "Richardson"
    );

    private static final List<String> VALID_SORT_OPTIONS = Arrays.asList(
            "PriceLowToHigh", "PriceHighToLow", "Newest"
    );

    public SearchResult searchItems(
            String queryText,
            String category,
            Double minPrice,
            Double maxPrice,
            String condition,
            String location,
            String sortBy,
            List<Item> itemDatabase
    ) {
        if (itemDatabase == null) {
            return new SearchResult(false, "System error: database unavailable", new ArrayList<>());
        }

        if (queryText == null) {
            return new SearchResult(false, "Error: invalid search text", new ArrayList<>());
        }

        String trimmedQuery = queryText.trim();
        if (trimmedQuery.isEmpty()) {
            return new SearchResult(false, "Error: invalid search text", new ArrayList<>());
        }

        if (category != null && !VALID_CATEGORIES.contains(category)) {
            return new SearchResult(false, "Error: invalid category", new ArrayList<>());
        }

        if (condition != null && !VALID_CONDITIONS.contains(condition)) {
            return new SearchResult(false, "Error: invalid condition", new ArrayList<>());
        }

        if (location != null && !VALID_LOCATIONS.contains(location)) {
            return new SearchResult(false, "Error: invalid location", new ArrayList<>());
        }

        if (sortBy != null && !VALID_SORT_OPTIONS.contains(sortBy)) {
            return new SearchResult(false, "Error: invalid sort option", new ArrayList<>());
        }

        if (minPrice != null && minPrice < 0) {
            return new SearchResult(false, "Error: invalid price range", new ArrayList<>());
        }

        if (maxPrice != null && maxPrice < 0) {
            return new SearchResult(false, "Error: invalid price range", new ArrayList<>());
        }

        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            return new SearchResult(false, "Error: invalid price range", new ArrayList<>());
        }

        List<Item> results = new ArrayList<>();

        for (Item item : itemDatabase) {
            if (!item.getTitle().toLowerCase().contains(trimmedQuery.toLowerCase())) {
                continue;
            }

            if (category != null && !item.getCategory().equals(category)) {
                continue;
            }

            if (minPrice != null && item.getPrice() < minPrice) {
                continue;
            }

            if (maxPrice != null && item.getPrice() > maxPrice) {
                continue;
            }

            if (condition != null && !item.getCondition().equals(condition)) {
                continue;
            }

            if (location != null && !item.getLocation().equals(location)) {
                continue;
            }

            results.add(item);
        }

        if (sortBy != null) {
            if (sortBy.equals("PriceLowToHigh")) {
                results.sort(Comparator.comparingDouble(Item::getPrice));
            } else if (sortBy.equals("PriceHighToLow")) {
                results.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
            } else if (sortBy.equals("Newest")) {
                results.sort((a, b) -> Long.compare(b.getCreatedAt(), a.getCreatedAt()));
            }
        }

        if (results.isEmpty()) {
            return new SearchResult(true, "No results found", results);
        }

        return new SearchResult(true, "Matching items found", results);
    }
}
