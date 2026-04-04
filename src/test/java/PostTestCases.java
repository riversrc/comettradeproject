import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTestCase {

    // all test cases for Post
    @Test
    void post_TC1() {
        // post successful
        assertEquals(PostGUI.("Desk", 650,"Slightly Scratched"), "A new post has been created.\n");
    }
    void post_TC2() {
        // post rejected for to long of a description
        String description = "";
        for(int i = 0; i < 401; i++)
            description = description + "E";
        assertEquals(PostGUI.("Laptop", 5000, ), "A field has an incorrect input, please try again.\n");
    }
    void post_TC3() {
        // post successful
        assertEquals(PostGUI.("Pillow", 10,""), "A new post has been created.\n");
    }
    void post_TC4() {
        // post rejected for too high a price
        assertEquals(PostGUI.("Tungsten", 40000,"Lab Equipment"), "A field has an incorrect input, please try again.\n");
    }
    void post_TC7() {
        // post rejected to too many decimal points
        assertEquals(PostGUI.("Apple", 80.999,"Just the core"), "A field has an incorrect input, please try again.\n");
    }
    void post_TC10() {
        // post rejected due to long title
        String title = "FLOATIE";
        for(int i = 0; i< 33; i++)
            title = title + "E";
        assertEquals(PostGUI.(title, 200,"Available for Summer 2026"), "A field has an incorrect input, please try again.\n");
    }
    void post_TC19() {
        // post rejected due to existing post
        assertEquals(PostGUI.("Desk", 50,"Very Low Use"), "This post already exists");
    }
}