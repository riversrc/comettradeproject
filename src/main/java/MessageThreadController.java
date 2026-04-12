import java.util.*;

public class MessageThreadController
{
    public static String accessThread(String currUser, String owner, String message)
    {
        if(!threadStatus(currUser, owner)){
            DBManager.createNewThread(currUser, owner, message);
            return "New message thread started";
        }
        DBManager.storeNewMessage(currUser, owner, message);
        return "Message sent";
    }
    
    public static List<String> getMessages(String currUser, String postOwner)
    {
        return DBManager.getThread(currUser, postOwner);
    }
    
    private static boolean threadStatus(String currUser, String postOwner)
    {
        return DBManager.checkThreadStatus(currUser, postOwner);
    }
}
