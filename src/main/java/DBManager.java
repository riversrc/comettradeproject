import java.util.HashMap;

public class DBManager
{
    private static HashMap<String, User> users = new HashMap<>();

    public DBManager() {
        // pre-existing user for test cases
        users.put("comettrade", new User("comettrade", "12345678a%"));
    }

    public boolean checkUserExist(String username) {
        if (users.containsKey(username)) {
            return true;
        }
        return false;
    }
    public static void storeUser(User user){
        users.put(user.getUsername(), user);
    }

    public static void storePost(String name, Post post)
    {
        users.get(name).addPost(name, post);
    }

    public static User getUser(String name)
    {
        return users.get(name);
    }
}