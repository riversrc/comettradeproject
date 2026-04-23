import java.util.*;

public class SearchBar
{
    private String query;

    public SearchBar(String query)
    {
        this.query = query;
    }

    /**
     * Checks if the input is valid then passes to SearchController.
     *
     * Valid input:
     *   - Not null
     *   - Length between 1 and 100 characters
     *   - Not blank / whitespace-only
     *   - Only letters, numbers, spaces, _ or .
     *
     * @return String[] of matching post titles, or empty array if invalid/no match
     */
    public String[] submitSearch()
    {
        if (!isValidInput(query))
            return new String[0];

        return SearchController.search(query);
    }

    /**
     * Validates the query string.
     *
     * @return true if valid, false otherwise
     */
    public static boolean isValidInput(String input)
    {
        if (input == null)
            return false;

        if (input.isBlank())
            return false;

        if (input.length() < 1 || input.length() > 100)
            return false;

        // Only letters, numbers, spaces, underscore, period allowed
        if (!input.matches("^[a-zA-Z0-9 _.]+$"))
            return false;

        return true;
    }

    public String getQuery()             { return query; }
    public void setQuery(String query)   { this.query = query; }
}