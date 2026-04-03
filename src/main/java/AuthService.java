public class AuthService {

    public boolean logout(String userId) {
        System.out.println("User logged out: " + userId);
        return true;
    }
    public boolean login(String userId) {
        System.out.println("User logged in: " + userId);
        return true;
    }
}