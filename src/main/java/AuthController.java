
public class AuthController {
    private AuthService authService = new AuthService();

    public String logout(String userId) {
        boolean success = authService.logout(userId);
        return success ? "Logout successful" : "Logout failed";
    }

    public String login(String userId) {
        boolean success = authService.login(userId);
        return success ? "Login successful" : "Login failed";
    }
}