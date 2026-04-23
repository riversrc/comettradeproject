import java.util.*;

public class SearchGUI
{
    /**
     * Validates input and returns matching post titles as a String[].
     * No display logic — frontend handles that later.
     *
     * @param input  the raw query typed by the user
     * @return       String[] of matching post titles, empty array if invalid or no match
     */
    public static String[] search(String input)
    {
        if (!SearchBar.isValidInput(input))
            return new String[0];

        SearchBar bar = new SearchBar(input);
        return bar.submitSearch();
    }
}