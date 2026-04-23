import java.util.*;

public class DBManager
{
    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, HashMap<String, List<String>>> threads = new HashMap<>();

    public DBManager() {
        // pre-existing user for test cases
        users.put("comettrade", new User("comettrade", "12345678a%"));
        users.put("bob", new User("bob", "12345678a%"));
        users.put("fred", new User("fred", "12345678a%"));
        users.put("12345", new User("12345", "12345678a%"));
        users.put("54321", new User("54321", "12345678a%"));
        // createNewThread("12345", "54321", "thank you");
    }

    public boolean checkUserExist(String username) {
        if (users.containsKey(username)) {
            return true;
        }
        return false;
    }
    public static void storeUser(User user){
        String name = user.getUsername();
        users.put(name, user);
        threads.put(name, new HashMap<String,List<String>>());
    }

    public static void storePost(String name, Post post)
    {
        users.get(name).addPost(name, post);
    }

    public static User getUser(String name)
    {
        return users.get(name);
    }
    
    public static boolean checkThreadStatus(String currUser, String postOwner)
    {
        return threads.get(currUser).containsKey(postOwner);
    }

    public static List<String> getThread(String currUser, String postOwner)
    {
        return threads.get(currUser).get(postOwner);
    }
    
    public static void createNewThread(String currUser, String postOwner, String msg)
    {
        List<String> thread = new ArrayList<>();
        thread.add(msg);
        threads.get(currUser).put(postOwner, thread);
        threads.get(postOwner).put(currUser, thread);
    }
    
    public static void storeNewMessage(String currUser, String postOwner, String msg)
    {
        threads.get(currUser).get(postOwner).add(msg);
        threads.get(postOwner).get(currUser).add(msg);
    }
    
    public static Iterator<Post> getAllPosts()
    {
        Set<String> allNames = users.keySet();
        List<Post> temp;
        List<Post> listing = new ArrayList<Post>();
        Iterator<Post> iter;
        for(String s : allNames){
            temp = users.get(s).getAllPosts();
            iter = temp.iterator();
            while(iter.hasNext())
                listing.add(iter.next());
        }
        return listing.iterator();
    }
}
