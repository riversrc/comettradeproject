import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchBar_Testing {

    // ─── isValidInput: Null / Blank ───────────────────────────────────────────

    @Test
    void searchBar_TC1() {
        // null input is invalid
        assertFalse(SearchBar.isValidInput(null));
    }

    @Test
    void searchBar_TC2() {
        // empty string is invalid
        assertFalse(SearchBar.isValidInput(""));
    }

    @Test
    void searchBar_TC3() {
        // whitespace-only string is invalid
        assertFalse(SearchBar.isValidInput("     "));
    }

    // ─── isValidInput: Length Boundaries ─────────────────────────────────────

    @Test
    void searchBar_TC4() {
        // single character is valid (lower boundary)
        assertTrue(SearchBar.isValidInput("a"));
    }

    @Test
    void searchBar_TC5() {
        // exactly 100 characters is valid (upper boundary)
        assertTrue(SearchBar.isValidInput("a".repeat(100)));
    }

    @Test
    void searchBar_TC6() {
        // 101 characters is invalid (exceeds upper boundary)
        assertFalse(SearchBar.isValidInput("a".repeat(101)));
    }

    // ─── isValidInput: Allowed Characters ────────────────────────────────────

    @Test
    void searchBar_TC7() {
        // letters only are valid
        assertTrue(SearchBar.isValidInput("LaptopReview"));
    }

    @Test
    void searchBar_TC8() {
        // letters, numbers, spaces, underscore, and period are all valid
        assertTrue(SearchBar.isValidInput("laptop_review 2024."));
    }

    @Test
    void searchBar_TC9() {
        // special character @ is invalid
        assertFalse(SearchBar.isValidInput("laptop@review"));
    }

    @Test
    void searchBar_TC10() {
        // special character ! is invalid
        assertFalse(SearchBar.isValidInput("!laptop"));
    }

    @Test
    void searchBar_TC11() {
        // hashtag character is invalid
        assertFalse(SearchBar.isValidInput("laptop#review"));
    }

    @Test
    void searchBar_TC12() {
        // hyphen is invalid (not in allowed set)
        assertFalse(SearchBar.isValidInput("laptop-review"));
    }

    // ─── submitSearch: Invalid Input Guard ───────────────────────────────────

    @Test
    void searchBar_TC13() {
        // submitSearch with null query returns empty array
        SearchBar sb = new SearchBar(null);
        assertArrayEquals(new String[0], sb.submitSearch());
    }

    @Test
    void searchBar_TC14() {
        // submitSearch with empty query returns empty array
        SearchBar sb = new SearchBar("");
        assertArrayEquals(new String[0], sb.submitSearch());
    }

    @Test
    void searchBar_TC15() {
        // submitSearch with invalid characters returns empty array
        SearchBar sb = new SearchBar("laptop@2024!");
        assertArrayEquals(new String[0], sb.submitSearch());
    }

    @Test
    void searchBar_TC16() {
        // submitSearch with query over 100 chars returns empty array
        SearchBar sb = new SearchBar("a".repeat(101));
        assertArrayEquals(new String[0], sb.submitSearch());
    }

    // ─── submitSearch: Valid Input ────────────────────────────────────────────

    @Test
    void searchBar_TC17() {
        // valid query with no DB match returns empty array (not null)
        SearchBar sb = new SearchBar("xyznotarealobject99999");
        assertArrayEquals(new String[0], sb.submitSearch());
    }

    // ─── getQuery / setQuery ──────────────────────────────────────────────────

    @Test
    void searchBar_TC18() {
        // getQuery returns the value passed to constructor
        SearchBar sb = new SearchBar("laptop");
        assertEquals("laptop", sb.getQuery());
    }

    @Test
    void searchBar_TC19() {
        // setQuery updates the stored query
        SearchBar sb = new SearchBar("oldquery");
        sb.setQuery("laptop");
        assertEquals("laptop", sb.getQuery());
    }

    @Test
    void searchBar_TC20() {
        // setQuery to invalid value causes submitSearch to return empty array
        SearchBar sb = new SearchBar("laptop");
        sb.setQuery("invalid@query!");
        assertArrayEquals(new String[0], sb.submitSearch());
    }
}
