import java.lang.Math;

public class Post
{
    private static String owner;
    private static double price;
    private static String title;
    private static String description;
    
    public Post()
    {
        
    }
    
    public boolean createPost(String t, double p, String d)
    {
        price = p;
        title = t;
        description = d;
        if(( (price<1) || (price>10000) ) || (Math.round(price * 100.0) != price * 100.0) ||(title.length()>40 || title.isEmpty()) || description.length()>300)
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

    @Override
    public String toString()
    {
        return "Product: " + "\n" + "Price: " + price + "$\n" 
            + "Description: " + description + "\n";
    }
}
