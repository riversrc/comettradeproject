import java.util.*;
import java.io.*;

public class DBManager {

    private static HashMap<String, User> users = new HashMap<>();
    private static HashMap<String, HashMap<String, List<String>>> threads = new HashMap<>();

    // NEW: messages map
    private static HashMap<String, List<String>> messages = new HashMap<>();

    private static final String USERS_FILE = "src/main/users.csv";
    private static final String POSTS_FILE = "src/main/posts.csv";
    private static final String MESSAGES_FILE = "src/main/messages.csv";

    // ================= STARTUP =================
    static {
        System.out.println("loading csv data...");
        ensureFilesExist();
        loadUsersFromCSV();
        loadPostsFromCSV();
        loadMessagesFromCSV();

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

            File messagesFile = new File(MESSAGES_FILE);
            if (!messagesFile.exists()) messagesFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================= LOAD USERS =================
    private static void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 2);
                if (data.length < 2) continue;

                String username = data[0];
                String password = data[1];

                users.put(username, new User(username, password));
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

    // ================= MESSAGE KEY =================
    private static String getKey(String user1, String user2) {
        return (user1.compareTo(user2) < 0)
                ? user1 + "|" + user2
                : user2 + "|" + user1;
    }

    // ================= LOAD MESSAGES =================
    private static void loadMessagesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(MESSAGES_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 2);
                if (data.length < 2) continue;

                String key = data[0];
                String msg = data[1];

                messages.putIfAbsent(key, new ArrayList<>());
                messages.get(key).add(msg);
            }

        } catch (IOException e) {
            System.out.println("No messages loaded.");
        }
    }

    // ================= SAVE ONE MESSAGE =================
    private static void appendMessageToCSV(String key, String msg) {
        try (FileWriter fw = new FileWriter(MESSAGES_FILE, true)) {
            fw.write(key + "," + msg.replace(",", " ") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
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

    // ================= MESSAGE METHODS =================

    public static boolean checkMessageThread(String user1, String user2) {
        return messages.containsKey(getKey(user1, user2));
    }

    public static List<String> getMessages(String user1, String user2) {
        return messages.get(getKey(user1, user2));
    }

    public static void createMessageThread(String user1, String user2, String msg) {
        String key = getKey(user1, user2);

        List<String> thread = new ArrayList<>();
        String formatted = user1 + ": " + msg;

        thread.add(formatted);
        messages.put(key, thread);

        appendMessageToCSV(key, formatted);
    }

    public static void appendMessage(String user1, String user2, String msg) {
        String key = getKey(user1, user2);
        String formatted = user1 + ": " + msg;

        if (!messages.containsKey(key)) {
            createMessageThread(user1, user2, msg);
            return;
        }

        messages.get(key).add(formatted);
        appendMessageToCSV(key, formatted);
    }
}