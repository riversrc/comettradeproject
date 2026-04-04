pubilc class PostGUI
{
    public PostGUI(){
        
    }
    public newPostStart(String title, double price, String description)
    {
        System.out.println(PostController.newPost(String title, double price, String description));
    }
}
