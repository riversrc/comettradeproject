import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Message_Create_Testing {

    // all test cases for create message
    @Test
    void messageCreate_TC1() {
        // message thread successfully created
        assertEquals(MessageThreadGUI.newMessageStart("bob", "fred", "thank you"), "New message thread started");
    }
    @Test
    void messageCreate_TC2() {
        // message is empty
        assertEquals(MessageThreadGUI.newMessageStart("bob", "fred", ""), "Message is empty");
    }
    @Test
    void messageCreate_TC3() {
        // message is too long
        assertEquals(MessageThreadGUI.newMessageStart("bob", "fred", "asdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdff"), "Message too long");
    }
    @Test
    void messageCreate_TC4() {
        // recieving user doesn't exist
        assertEquals(MessageThreadGUI.newMessageStart("bob", "doesnotexist", "thank you"), "Receiving User ID doesn't exist");
    }
    @Test
    void messageCreate_TC7() {
        // whitespace or special characters used in recieving user id
        assertEquals(MessageThreadGUI.newMessageStart("bob", "1$% as", "thank you"), "Receiving User ID has bad characters");
    }
    @Test
    void messageCreate_TC10() {
        // sending user id doesn't exist
        assertEquals(MessageThreadGUI.newMessageStart("doesnotexist", "fred", "thank you"), "Sending User ID doesn't exist");
    }
    @Test
    void messageCreate_TC19() {
        // sending user id has whitespace or special characters
        assertEquals(MessageThreadGUI.newMessageStart("1$% as", "fred", "thank you"), "Sending User ID has bad characters");
    }





}