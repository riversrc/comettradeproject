public class PostController
{
    private static Post post = new Post();
    public static String currUser = "comettrade";
    private static boolean check;

    public static String newPost(String title, double price, String description)
    {
        User user = DBManager.getUser(currUser);
        if(user.hasPost(title))
            return "This post already exists";
        
        check = post.createPost(title, price, description);
        
        if(check){
            DBManager.storePost(currUser, post);
            return "A new post has been created.\n";
        }else
            return "A field has an incorrect input, please try again.\n";
    }
}
