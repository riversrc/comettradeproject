import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Search_Testing {

    @Test
    void search_TC1() {
        // valid search
        Object result = SearchGUI.search("thank");

        assertTrue(result instanceof List);
    }

    @Test
    void search_TC2() {
        // empty search
        assertEquals("Error: Search query is empty",
                SearchGUI.search(""));
    }

    @Test
    void search_TC3() {
        // too long search
        assertEquals("Error: Search query too long",
                SearchGUI.search("asdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdff"));
    }

    @Test
    void search_TC4() {
        // bad characters
        assertEquals("Error: Search query has invalid characters",
                SearchGUI.search("bad/query"));
    }

    @Test
    void search_TC5() {
        // no results found
        assertEquals("No results found",
                SearchGUI.search("zzzzzz"));
    }
}