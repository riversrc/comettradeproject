import java.util.*;

public class MessageThreadGUI
{
    private static String postOwner;
    
    public MessageThreadGUI(String owner)
    {
        postOwner = owner;
    }

    public static List<String> allMessages(String sendingUser, String receivingUser)
    {
        return MessageThreadController.getMessages(sendingUser, receivingUser);
    }
    
    public static String newMessageStart(String sendingUser, String receivingUser, String message)
    {
        return MessageThreadController.accessThread(sendingUser, receivingUser, message);
    }

    public static String replyMessage(String sendingUser, String receivingUser, String message) {
        return MessageThreadController.replyMessage(sendingUser, receivingUser, message);
    }
}