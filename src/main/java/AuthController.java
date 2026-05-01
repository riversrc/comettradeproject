import java.util.Objects;

public class AuthController {
    private AuthService authService = new AuthService();
    private DBManager dbManager = new DBManager();


    public String logout(String userId) {
        boolean success = authService.logout(userId);
        return success ? "Logout successful" : "Logout failed";
    }

    public Boolean checkUserExist(String userId) {
        if(dbManager.checkUserExist(userId)) {
            return true;
        }
        return false;
    }

    public Boolean checkPwdMatches(String userId, String pwd) {
        User user = dbManager.getUser(userId);
        if (Objects.equals(pwd, user.checkPassword())) {
            return true;
        }
        return false;
    }

    public String signup(String username, String password) {

        // username validation
        if (username == null || username.isEmpty()) {
            return "Error: Exceptional username (empty)";
        }

        if (username.contains(" ") || username.contains("/") || username.contains("\\")) {
            return "Error: Username has bad characters";
        }

        if (checkUserExist(username)) {
            return "Error: Username already exists";
        }

        // password validation
        if (password == null || password.isEmpty()) {
            return "Error: Exceptional password (null)";
        }

        if (password.length() < 8 || password.length() > 24) {
            return "Error: Password must be 8-24 characters";
        }

        // store user
        User newUser = new User(username, password);
        DBManager.storeUser(newUser);

        return "User created successfully";
    }
}