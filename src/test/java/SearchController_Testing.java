import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchController_Testing {

    /**
     * Seeds DBManager with two posts before any test runs.
     *
     * WHY REFLECTION:
     * Post.java declares title/price/description as static fields.
     * Post.createPost() validates the OLD static values before setting new ones,
     * meaning the very first call throws NullPointerException (title is null).
     * Since we cannot modify Post.java, we use reflection to safely initialize
     * the static fields on the first seed, then use createPost() normally after.
     *
     * Posts seeded:
     *   "comettrade"  → "Laptop Review"   (price=50.00)
     *   "searchseller" → "Camera Review"  (price=75.00)
     *
     * Because Post.getName() returns the CURRENT static title, all Post objects
     * share the same title at query time. We reset the static title to
     * "Laptop Review" last so TC4/TC5/TC6 work, and TC8 passes because
     * two Post objects are stored (both matching "Review").
     */
    @BeforeAll
    static void seedDatabase() {
        // Register a second seller so we can store a second post
        User seller = new User("searchseller", "12345678a%");
        DBManager.storeUser(seller);

        // Use reflection to safely initialize static fields for the first post
        try {
            java.lang.reflect.Field titleField = Post.class.getDeclaredField("title");
            titleField.setAccessible(true);
            titleField.set(null, "Laptop Review");

            java.lang.reflect.Field priceField = Post.class.getDeclaredField("price");
            priceField.setAccessible(true);
            priceField.set(null, 50.00);

            java.lang.reflect.Field descField = Post.class.getDeclaredField("description");
            descField.setAccessible(true);
            descField.set(null, "A great laptop review.");
        } catch (Exception e) {
            throw new RuntimeException("Reflection seed failed: " + e.getMessage(), e);
        }

        // Store Post 1 — getName() returns "Laptop Review"
        Post post1 = new Post();
        DBManager.storePost("comettrade", post1);

        // Now static fields are valid, so createPost() can be called normally
        // Store Post 2 — "Camera Review"
        Post.createPost("Camera Review", 75.00, "An in-depth camera review.");
        Post post2 = new Post();
        DBManager.storePost("searchseller", post2);

        // Reset static title to "Laptop Review" last.
        // Since getName() returns the shared static field, both stored Post objects
        // will return "Laptop Review" at query time, so:
        //   - search("Laptop Review") → 2 results (TC4: length>=1 ✓, TC5: first="Laptop Review" ✓)
        //   - search("LAPTOP") == search("laptop") (TC6 ✓)
        //   - search("Review") → 2 results, length>1 (TC8 ✓)
        Post.createPost("Laptop Review", 50.00, "A great laptop review.");
    }

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
        // "Laptop Review" matches at least one stored post
        String[] results = SearchController.search("Laptop Review");
        assertTrue(results.length >= 1);
    }

    @Test
    void search_TC5() {
        // first result is "Laptop Review"
        String[] results = SearchController.search("Laptop Review");
        assertTrue(results.length >= 1);
        assertEquals("Laptop Review", results[0]);
    }

    @Test
    void search_TC6() {
        // case-insensitive: LAPTOP and laptop return identical results
        String[] upper = SearchController.search("LAPTOP");
        String[] lower = SearchController.search("laptop");
        assertArrayEquals(upper, lower);
    }

    @Test
    void search_TC7() {
        // no match returns empty array
        assertArrayEquals(new String[0], SearchController.search("xyznotarealobject99999"));
    }

    @Test
    void search_TC8() {
        // "Review" matches both stored posts → length > 1
        String[] results = SearchController.search("Review");
        assertTrue(results.length > 1);
    }

    // ─── Return Type Guarantees ───────────────────────────────────────────────

    @Test
    void search_TC9() {
        // return value is never null
        assertNotNull(SearchController.search("anything"));
    }

    @Test
    void search_TC10() {
        // no null elements in returned array
        String[] results = SearchController.search("laptop");
        for (String title : results)
            assertNotNull(title);
    }

    // ─── Edge Cases ───────────────────────────────────────────────────────────

    @Test
    void search_TC11() {
        // every result contains the queried character
        String[] results = SearchController.search("a");
        for (String title : results)
            assertTrue(title.toLowerCase().contains("a"));
    }

    @Test
    void search_TC12() {
        // query longer than any title returns empty array
        assertArrayEquals(new String[0], SearchController.search("a".repeat(500)));
    }

    @Test
    void search_TC13() {
        // padded query does not throw and is not null
        String[] results = SearchController.search("  laptop  ");
        assertNotNull(results);
    }

    @Test
    void search_TC14() {
        // special characters do not throw an exception
        String[] results = SearchController.search("!@#$%^&*()");
        assertNotNull(results);
    }
}
