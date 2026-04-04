public class PostGUI
{
    public static String newPostStart(String title, double price, String description)
    {
        return PostController.newPost(title, price, description);
    }
}