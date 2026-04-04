import java.lang.Math;
import java.util.String;

public class Post
{
    private double price;
    private String title;
    private String description;
    
    public Post()
    {
        
    }
    
    public boolean createPost(String title, double price, String description)
    {
        if((price<1&&price>10000)||(fmod(price, 0.01))!=0||(title.length()>40&&title.length<1)||description.length()>300)
        {
            return false;
        }
        this.price = price;
        this.title = title;
        this.description = description;
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
