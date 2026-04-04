pubilc class PostGUI
{
    public String newPostStart(String title, double price, String description)
    {
        return PostController.newPost(String title, double price, String description);
    }
}
