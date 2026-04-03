import java.util.HashMap;

public class DBManager
{
    private HashMap<String, User> users = new HashMap<>();

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
    public User getUser(String username) {
        return users.get(username);
    }


    public void storePost(String name){

    }
}