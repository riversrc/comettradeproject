import java.util.*;

public class MessageThreadController
{
    public static String accessThread(String sendingUser, String receivingUser, String message)
    {
        User postOwner = DBManager.getUser(receivingUser);
        if(!threadStatus(sendingUser, receivingUser)){
            DBManager.createNewThread(sendingUser, receivingUser, message);
            return "New message thread started";
        }
        DBManager.storeNewMessage(sendingUser, receivingUser, message);
        return "Message sent";
    }

    public static String replyMessage(String sendingUser, String receivingUser, String message) {
        if (!DBManager.checkThreadStatus(sendingUser, receivingUser)) {
            return "Thread not found";
        }
        DBManager.storeNewMessage(sendingUser, receivingUser, message);
        return "Message sent successfully";
    }

    public static List<String> getMessages(String sendingUser, String receivingUser)
    {
        return DBManager.getThread(sendingUser, receivingUser);
    }
    
    private static boolean threadStatus(String sendingUser, String receivingUser)
    {
        return DBManager.checkThreadStatus(sendingUser, receivingUser);
    }
}
