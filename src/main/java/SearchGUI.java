public class SearchGUI {

    public static Object search(String query) {
        return SearchController.searchPosts(query);
    }
}