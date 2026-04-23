import java.util.*;

public class MessageThreadGUI
{
    private static String currUser = "comettrade";
    private static String postOwner;
    
    public MessageThreadGUI(String owner)
    {
        postOwner = owner;
    }

    public static List<String> allMessages(String currUser, String owner)
    {
        return MessageThreadController.getMessages(currUser, owner);
    }
    
    public static String newMessageStart(String title, String currUser, String owner, String description)
    {
        return MessageThreadController.accessThread(title, currUser, owner, description);
    }
}