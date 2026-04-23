import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Message_Reply_Testing {

    // all test cases for reply message
    @Test
    void replyMessage_TC1() {
        // message thread replied created
        assertEquals(MessageThreadGUI.replyMessage("54321", "12345", "Yes, the item is still available. I can answer any questions."), "Message sent successfully");
    }
    @Test
    void replyMessage_TC2() {
        // message is empty
        assertEquals(MessageThreadGUI.replyMessage("54321", "12345", ""), "Error: Invalid message (empty message)");
    }
    @Test
    void replyMessage_TC3() {
        // receivingUser doesnt exist
        assertEquals(MessageThreadGUI.replyMessage("54321", "999", "Yes, it is available"), "Error: Invalid receivingUser (user not found)");
    }
    @Test
    void replyMessage_TC4() {
        // receivingUser exceptional (null)
        assertEquals(MessageThreadGUI.replyMessage("54321", "", "Yes, it is available"), "Error: Exceptional receivingUser (null)");
    }
    @Test
    void replyMessage_TC5() {
        // sendingUser doesnt exist
        assertEquals(MessageThreadGUI.replyMessage("999", "12345", "Yes, it is available"), "Error: Invalid sendingUser (user not found)");
    }
    @Test
    void replyMessage_TC6() {
        // Message exceeds character limit of 100 chars
        assertEquals(MessageThreadGUI.replyMessage("54321", "12345", "asdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdfasdasdasdff"), "Error: Character limit exceeded");
    }
    @Test
    void replyMessage_TC7() {
        // sendingUser exceptional (null)
        assertEquals(MessageThreadGUI.replyMessage("", "12345", "Yes, it is available"), "Error: Exceptional sendingUser (null)");
    }
}