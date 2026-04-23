import java.util.*;

public class SearchController
{
    /**
     * Searches all posts in DBManager for ones whose title
     * contains the query string, then returns their titles in a new String[].
     *
     * @param query  the search string typed by the user
     * @return       String[] of matching post titles, empty array if none found
     */
    public static String[] search(String query)
    {
        if (query == null || query.isBlank())
            return new String[0];

        List<String> results = new ArrayList<String>();

        Iterator<Post> allPosts = DBManager.getAllPosts();

        while (allPosts.hasNext())
        {
            Post post = allPosts.next();

            if (post.getName() != null &&
                post.getName().toLowerCase().contains(query.toLowerCase()))
            {
                results.add(post.getName());
            }
        }

        return results.toArray(new String[0]);
    }
}