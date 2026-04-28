import java.util.*;
import java.io.*;

public class DBManager {

    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, HashMap<String, List<String>>> threads = new HashMap<>();

    private static final String USERS_FILE = "src/main/users.csv";
    private static final String POSTS_FILE = "src/main/posts.csv";

    // ================= STARTUP =================
    static {
        System.out.println("loading csv data...");
        ensureFilesExist();
        loadUsersFromCSV();
        loadPostsFromCSV();

        for (String name : users.keySet()) {
            threads.put(name, new HashMap<>());
        }
    }

    // ================= FILE SETUP =================
    private static void ensureFilesExist() {
        try {
            File usersFile = new File(USERS_FILE);
            if (!usersFile.exists()) usersFile.createNewFile();

            File postsFile = new File(POSTS_FILE);
            if (!postsFile.exists()) postsFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================= LOAD USERS =================
    private static void loadUsersFromCSV() {
        System.out.println("Loading users from CSV...");

        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 2);
                if (data.length < 2) continue;

                String username = data[0];
                String password = data[1];

                users.put(username, new User(username, password));
                System.out.println(username + " " + password);
            }

        } catch (IOException e) {
            System.out.println("No users loaded.");
        }
    }

    // ================= LOAD POSTS =================
    private static void loadPostsFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(POSTS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 4);
                if (data.length < 4) continue;

                String username = data[0];
                String title = data[1];
                double price = Double.parseDouble(data[2]);
                String description = data[3];

                if (users.containsKey(username)) {
                    Post post = new Post();
                    post.createPost(title, price, description);

                    users.get(username).addPost(title, post);
                }
            }

        } catch (IOException e) {
            System.out.println("No posts loaded.");
        }
    }

    // ================= USER METHODS =================
    public static boolean checkUserExist(String username) {
        return users.containsKey(username);
    }

    public static void storeUser(User user) {
        String name = user.getUsername();
        users.put(name, user);
        threads.put(name, new HashMap<>());

        try (FileWriter fw = new FileWriter(USERS_FILE, true)) {
            fw.append(name)
                    .append(",")
                    .append(user.checkPassword())
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String name) {
        return users.get(name);
    }

    // ================= POSTS =================
    public static void storePost(String name, Post post) {
        users.get(name).addPost(post.getName(), post);

        try (FileWriter fw = new FileWriter(POSTS_FILE, true)) {
            fw.append(name).append(",")
                    .append(post.getName()).append(",")
                    .append(String.valueOf(post.getPrice())).append(",")
                    .append(post.getDescription().replace(",", " "))
                    .append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Iterator<Post> getAllPosts() {
        List<Post> listing = new ArrayList<>();

        for (String s : users.keySet()) {
            listing.addAll(users.get(s).getAllPosts());
        }

        return listing.iterator();
    }

    // ================= THREADS =================
    public static boolean checkThreadStatus(String currUser, String postOwner) {
        return threads.get(currUser).containsKey(postOwner);
    }

    public static List<String> getThread(String currUser, String postOwner) {
        return threads.get(currUser).get(postOwner);
    }

    public static void createNewThread(String currUser, String postOwner, String msg) {
        List<String> thread = new ArrayList<>();
        thread.add(msg);

        threads.get(currUser).put(postOwner, thread);
        threads.get(postOwner).put(currUser, thread);
    }

    public static void storeNewMessage(String currUser, String postOwner, String msg) {
        threads.get(currUser).get(postOwner).add(msg);
        threads.get(postOwner).get(currUser).add(msg);
    }
}