import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Signup_Testing {

    @Test
    void signup_TC1() {
        assertEquals("User created successfully",
                SignupGUI.signup("newuser", "password123"));
    }

    @Test
    void signup_TC2() {
        assertEquals("Error: Username already exists",
                SignupGUI.signup("bob", "password123"));
    }

    @Test
    void signup_TC3() {
        assertEquals("Error: Username has bad characters",
                SignupGUI.signup("bad user", "password123"));
    }

    @Test
    void signup_TC4() {
        assertEquals("Error: Exceptional password (null)",
                SignupGUI.signup("uniqueuser", ""));
    }

    @Test
    void signup_TC5() {
        assertEquals("Error: Password must be 8-24 characters",
                SignupGUI.signup("uniqueuser2", "short"));
    }
}