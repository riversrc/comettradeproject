public class Post
{
    private String owner;
    private double price;
    private String title;
    private String description;

    public Post()
    {
    }

    public Post(String title, double price, String description)
    {
        createPost(title, price, description);
    }

    public boolean createPost(String t, double p, String d)
    {
        price = p;
        title = t;
        description = d;

        if ((price < 1) || (price > 10000) ||
                (Math.round(price * 100.0) != price * 100.0) ||
                (title.length() > 40 || title.isEmpty()) ||
                description.length() > 300)
        {
            return false;
        }

        return true;
    }

    public String getName()
    {
        return title;
    }

    public double getPrice()
    {
        return price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getOwner()
    {
        return owner;
    }

    @Override
    public String toString()
    {
        return "Product:\n" +
                "Title: " + title + "\n" +
                "Price: " + price + "$\n" +
                "Description: " + description + "\n";
    }
}