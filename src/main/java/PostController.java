public class PostController
{
    public static String currUser = "comettrade";

    public static String newPost(String title, double price, String description)
    {
        User user = DBManager.getUser(currUser);

        if (user == null) {
            return "User not found.\n";
        }

        if (user.hasPost(title)) {
            return "This post already exists.\n";
        }

        Post post = new Post();

        boolean check = post.createPost(title, price, description);

        if (check) {
            DBManager.storePost(currUser, post);
            return "A new post has been created.\n";
        } else {
            return "A field has an incorrect input, please try again.\n";
        }
    }
}