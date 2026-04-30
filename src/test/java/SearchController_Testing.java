import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchController_Testing {

    // ─── Null / Blank Guard ───────────────────────────────────────────────────

    @Test
    void search_TC1() {
        // null query returns empty array
        assertArrayEquals(new String[0], SearchController.search(null));
    }

    @Test
    void search_TC2() {
        // empty string returns empty array
        assertArrayEquals(new String[0], SearchController.search(""));
    }

    @Test
    void search_TC3() {
        // whitespace-only query returns empty array
        assertArrayEquals(new String[0], SearchController.search("   "));
    }

    // ─── Matching Logic ───────────────────────────────────────────────────────

    @Test
    void search_TC4() {
        // exact title match returns array with length 1
        String[] results = SearchController.search("Laptop Review");
        assertEquals(1, results.length);
    }

    @Test
    void search_TC5() {
        // exact title match returns the correct title
        String[] results = SearchController.search("Laptop Review");
        assertEquals("Laptop Review", results[0]);
    }

    @Test
    void search_TC6() {
        // uppercase query returns same results as lowercase query
        String[] upper = SearchController.search("LAPTOP");
        String[] lower = SearchController.search("laptop");
        assertArrayEquals(upper, lower);
    }

    @Test
    void search_TC7() {
        // query with no matching posts returns empty array
        assertArrayEquals(new String[0], SearchController.search("xyznotarealobject99999"));
    }

    @Test
    void search_TC8() {
        // query matching multiple posts returns more than one result
        String[] results = SearchController.search("Review");
        assertEquals(true, results.length > 1);
    }

    // ─── Return Type Guarantees ───────────────────────────────────────────────

    @Test
    void search_TC9() {
        // return value is never null
        assertEquals(false, SearchController.search("anything") == null);
    }

    @Test
    void search_TC10() {
        // returned array contains no null elements
        String[] results = SearchController.search("laptop");
        for (String title : results)
            assertEquals(false, title == null);
    }

    // ─── Edge Cases ───────────────────────────────────────────────────────────

    @Test
    void search_TC11() {
        // single character query only returns titles containing that character
        String[] results = SearchController.search("a");
        for (String title : results)
            assertEquals(true, title.toLowerCase().contains("a"));
    }

    @Test
    void search_TC12() {
        // query longer than any post title returns empty array
        assertArrayEquals(new String[0], SearchController.search("a".repeat(500)));
    }

    @Test
    void search_TC13() {
        // padded query with spaces is not blank so search runs,
        // but contains("  laptop  ") won't match trimmed titles
        String[] results = SearchController.search("  laptop  ");
        assertEquals(false, results == null);
    }

    @Test
    void search_TC14() {
        // special characters in query do not throw an exception
        String[] results = SearchController.search("!@#$%^&*()");
        assertEquals(false, results == null);
    }
}
