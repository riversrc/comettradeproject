import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Message_Create_Testing {

    // all test cases for create message
    @Test
    void login_TC1() {
        // message thread successfully created
        assertEquals(MessageThreadGUI.newMessageStart("comettrade", "12345678a%"), "Login successful");
    }
    @Test
    void login_TC2() {
        // message is empty
        assertEquals(MessageThreadGUI.newMessageStart("comettrade", "123456789101112a%"), "Password does not match length requirement");
    }
    @Test
    void login_TC3() {
        // message is too long
        assertEquals(MessageThreadGUI.newMessageStart("comettrade", "123456 78a%"), "Password contains invalid characters");
    }
    @Test
    void login_TC4() {
        // recieving user doesn't exist
        assertEquals(MessageThreadGUI.newMessageStart("comettraderr", "12345678a%"), "Username does not exist");
    }
    @Test
    void login_TC7() {
        // whitespace or special characters used in recieving user id
        assertEquals(MessageThreadGUI.newMessageStart("comet trade", "12345678a%"), "Username contains invalid characters");
    }
    @Test
    void login_TC10() {
        // sending user id doesn't exist
        assertEquals(MessageThreadGUI.newMessageStart("comet trade", "12345678a%"), "Username contains invalid characters");
    }
    @Test
    void login_TC19() {
        // sending user id has whitespace or special characters
        assertEquals(MessageThreadGUI.newMessageStart("comet trade", "12345678a%"), "Username contains invalid characters");
    }





}