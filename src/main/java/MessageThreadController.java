import java.util.*;

public class MessageThreadController
{
    public static String accessThread(String post, String currUser, String owner, String message)
    {
        User postOwner = DBManager.getUser(owner);
        if(!postOwner.checkPost(post)){
            return "Invalid post, message request denied";
        }
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
