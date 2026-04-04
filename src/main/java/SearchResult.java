import java.util.List;

public class SearchResult {
    private boolean success;
    private String message;
    private List<Item> items;

    public SearchResult(boolean success, String message, List<Item> items) {
        this.success = success;
        this.message = message;
        this.items = items;
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
}
