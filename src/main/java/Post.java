import java.lang.Math;

public class Post
{
    private static double price;
    private static String title;
    private static String description;
    
    public Post()
    {
        
    }
    
    public static boolean createPost(String t, double p, String d)
    {
        if((price<1&&price>10000)||(price%0.01)!=0||(title.length()>40&&title.length()<1)||description.length()>300)
        {
            return false;
        }
        price = p;
        title = t;
        description = d;
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