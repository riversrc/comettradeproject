import java.util.*;

public class User {
    private String username;
    private String password;
    private HashMap<String, Post> posts = new HashMap<>();

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String checkPassword(){
        return password;
    }

    public void addPost(String name, Post post){
        posts.put(name, post);
    }

    public Post getPost(String name){
        return posts.get(name);
    }
    
    public List<Post> getAllPosts(){
        List<Post> allPosts = new ArrayList<Post>();
        Set<String> postName = posts.keySet();
        
        for(String s : postName)
            allPosts.add(posts.get(s));
        return allPosts;
    }

    public boolean hasPost(String title){
        return posts.containsKey(title);
    }
}
