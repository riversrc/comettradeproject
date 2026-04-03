import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Login_UseCase_Testing {

    // all test cases for login
    @Test
    void login_TC1() {
        // login successful
        assertEquals(LoginGUI.login("comettrade", "12345678a%"), "Login successful");
    }
    @Test
    void login_TC2() {
        // password is too long or incorrect password
        assertEquals(LoginGUI.login("comettrade", "123456789101112a%"), "Password does not match length requirement");
    }
    @Test
    void login_TC3() {
        // password has space or control characters
        assertEquals(LoginGUI.login("comettrade", "123456 78a%"), "Password contains invalid characters");
    }
    @Test
    void login_TC4() {
        // username doesn't exist
        assertEquals(LoginGUI.login("comettraderr", "12345678a%"), "Username does not exist");
    }
    @Test
    void login_TC5() {
        // username includes space or invalid character
        assertEquals(LoginGUI.login("comet trade", "12345678a%"), "Username contains invalid characters");
    }





}