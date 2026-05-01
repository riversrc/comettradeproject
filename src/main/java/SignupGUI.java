public class SignupGUI {

    private static AuthController authController = new AuthController();

    public static String signup(String username, String password) {
        return authController.signup(username, password);
    }
}
