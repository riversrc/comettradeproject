import java.util.*;

public class SearchController {

    // validation helpers (mirroring your style)
    public static boolean checkSearchEmpty(String query) {
        return query.isEmpty();
    }

    public static boolean checkSearchTooLong(String query) {
        return query.length() > 100;
    }

    public static boolean checkBadCharacters(String query) {
        char[] badChars = {'/', '\\'};
        for (char c : badChars) {
            if (query.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }

    // main search function
    public static Object searchPosts(String query) {

        if (checkSearchEmpty(query)) {
            return "Error: Search query is empty";
        }

        if (checkSearchTooLong(query)) {
            return "Error: Search query too long";
        }

        if (checkBadCharacters(query)) {
            return "Error: Search query has invalid characters";
        }

        Iterator<Post> allPosts = DBManager.getAllPosts();
        List<Post> results = new ArrayList<>();

        while (allPosts.hasNext()) {
            Post p = allPosts.next();

            // assuming Post has getContent()
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(p);
            }
        }

        if (results.isEmpty()) {
            return "No results found";
        }

        return results;
    }
}