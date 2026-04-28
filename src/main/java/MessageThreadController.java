import java.util.*;

public class MessageThreadController
{



    //////////////////////////////////////
    // testing functions for test cases //
    //////////////////////////////////////
    public static char[] badChars = {' ', ',', '/', '\\', ' '};

    public static boolean checkIDForBadCharacters(String userID) {
        // check if username has non-valid characters
        for(char c : badChars){
            if(userID.indexOf(c) >= 0){
                return true;
            }
        }

        // check for control characters in username
        for(char c : userID.toCharArray()){
            if( Character.isISOControl(c)){

                return true;
            }
        }
        // returns false if there are no bad characters
        return false;
    }

    public static boolean checkUserIDEmpty(String userID) {
        if(userID.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkUserExists(String userID) {
        if (DBManager.checkUserExist(userID)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkMsgTooLong(String message) {
        if (message.length() > 100) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkMsgEmpty(String message) {
        if (message.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    ///////////////////////
    // message functions //
    ///////////////////////
    public static String accessThread(String sendingUser, String receivingUser, String message)
    {

        if(checkIDForBadCharacters(sendingUser)){
            return "Sending User ID has bad characters";
        }
        if(!checkUserExists(sendingUser)){
            return "Sending User ID doesn't exist";
        }
        if(checkIDForBadCharacters(receivingUser)){
            return "Receiving User ID has bad characters";
        }
        if(!checkUserExists(receivingUser)){
            return "Receiving User ID doesn't exist";
        }
        if(checkMsgTooLong(message)){
            return "Message too long";
        }
        if(checkMsgEmpty(message)){
            return "Message is empty";
        }

        if(!threadStatus(sendingUser, receivingUser)){
            DBManager.createNewThread(sendingUser, receivingUser, message);
            return "New message thread started";
        }
        DBManager.storeNewMessage(sendingUser, receivingUser, message);
        return "Message sent";
    }

    public static String replyMessage(String sendingUser, String receivingUser, String message) {

        if (checkUserIDEmpty(sendingUser)) {
            return "Error: Exceptional sendingUser (null)";
        }
        if (checkMsgTooLong(message)) {
            return "Error: Character limit exceeded";
        }
        if (!checkUserExists(sendingUser)) {
            return "Error: Invalid sendingUser (user not found)";
        }
        if (checkUserIDEmpty(receivingUser)) {
            return "Error: Exceptional receivingUser (null)";
        }
        if(!checkUserExists(receivingUser)){
            return "Error: Invalid receivingUser (user not found)";
        }
        if(checkMsgEmpty(message)){
            return "Error: Invalid message (empty message)";
        }
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
