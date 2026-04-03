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
}