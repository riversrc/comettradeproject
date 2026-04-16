import java.util.List;
import java.util.Collections;

public class SearchResult {
    private boolean success;
    private String message;
    private List<Item> items;

    public enum ResultType {
        SUCCESS,
        NO_RESULTS,
        INVALID_QUERY,
        INVALID_CATEGORY,
        INVALID_PRICE,
        INVALID_CONDITION,
        INVALID_LOCATION,
        INVALID_SORT,
        SYSTEM_ERROR
    }

    private ResultType resultType;

    public SearchResult(boolean success, String message, List<Item> items, ResultType resultType) {
        this.success = success;
        this.message = message;
        this.items = (items != null) ? items : Collections.emptyList();
        this.resultType = resultType;
    }

    public static SearchResult success(List<Item> items) {
        return new SearchResult(true, "Items found successfully.", items, ResultType.SUCCESS);
    }

    public static SearchResult noResults() {
        return new SearchResult(true, "No matching items found.", Collections.emptyList(), ResultType.NO_RESULTS);
    }

    public static SearchResult invalidQuery() {
        return new SearchResult(false, "Error: Invalid search text. Must be 1–100 characters.", Collections.emptyList(), ResultType.INVALID_QUERY);
    }

    public static SearchResult invalidCategory(String category) {
        return new SearchResult(false, "Error: Invalid category '" + category + "'.", Collections.emptyList(), ResultType.INVALID_CATEGORY);
    }

    public static SearchResult invalidPriceRange(double minPrice, double maxPrice) {
        return new SearchResult(false, "Error: Invalid price range. MinPrice=" + minPrice + " MaxPrice=" + maxPrice, Collections.emptyList(), ResultType.INVALID_PRICE);
    }

    public static SearchResult invalidCondition(String condition) {
        return new SearchResult(false, "Error: Invalid condition '" + condition + "'. Must be New, Like New, Good, or Fair.", Collections.emptyList(), ResultType.INVALID_CONDITION);
    }

    public static SearchResult invalidLocation(String location) {
        return new SearchResult(false, "Error: Unsupported location '" + location + "'.", Collections.emptyList(), ResultType.INVALID_LOCATION);
    }

    public static SearchResult invalidSortOption(String sortBy) {
        return new SearchResult(false, "Error: Invalid sort option '" + sortBy + "'. Must be PriceLowToHigh, PriceHighToLow, or Newest.", Collections.emptyList(), ResultType.INVALID_SORT);
    }

    public static SearchResult systemError() {
        return new SearchResult(false, "System error: Service unavailable. Please try again later.", Collections.emptyList(), ResultType.SYSTEM_ERROR);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Item> getItems() {
        return items;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
