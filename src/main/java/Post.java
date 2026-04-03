public class Post
{
    private int price;
    private String name;
    private String description;

    public Post(int price, String name, String description)
    {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Product: " + "\n" + "Price: " + price + "$\n"
                + "Description: " + description + "\n";
    }
}