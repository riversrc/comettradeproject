public class LoginGUI {
    public static final int MIN_LENGTH_USERNAME = 8;
    public static final int MAX_LENGTH_USERNAME = 20;

    public static final int MIN_LENGTH_PWD = 8;
    public static final int MAX_LENGTH_PWD = 12;

    public static char[] badChars = {' ', ',', '/', '\\', ' '};
    private static AuthController authController = new AuthController();

    // username checks

    public static boolean checkUsernameLength(String username){
        // check if length is valid for username input
        if(MIN_LENGTH_USERNAME <= username.length()  &&  username.length() <= MAX_LENGTH_USERNAME){
            // returns true if username is within required length
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkUsernameForBadCharacters(String username){
        // check if username has non-valid characters
        for(char c : badChars){
            if(username.indexOf(c) >= 0){
                return true;
            }
        }

        // check for control characters in username
        for(char c : username.toCharArray()){
            if( Character.isISOControl(c)){

                return true;
            }
        }
        // returns false if there are no bad characters
        return false;
    }

    public static boolean checkLoginExisting(String username){
        // check if username exist in database, returns true
        if (authController.checkUserExist(username)) {
            return true;
        } else {
            return false;
        }
    }

    // password checks

    public static boolean checkPwdLength(String password){
        if(MIN_LENGTH_PWD <= password.length()  &&  password.length() <= MAX_LENGTH_PWD){
            // returns true if password is within required length
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkPwdForValidCharacters(String argPwd) {
        // check for required characters
        boolean foundLetter = false;
        boolean foundNumber = false;
        boolean foundSpecialChar = false;
        for (char c : argPwd.toCharArray()) {
            if (Character.isISOControl(c) || Character.isSpaceChar(c)) {
                return false;
            } else if (Character.isLetter(c)) {
                foundLetter = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else {
                foundSpecialChar = true;
            }
        }
        // returns true if required characters found
        return( foundLetter && foundNumber && foundSpecialChar );
    }

    public static boolean passwordCheck(String username, String password){
        // if username exist, this checks if password matches user credentials, if so login successful
        if (authController.checkPwdMatches(username, password)) {
            return true;
        }
        return false;
    }

    // login
    public static String login(String username, String password){
        if (!checkUsernameLength(username)){
            return "Username does not satisfy length requirement";
        }
        if (checkUsernameForBadCharacters(username)){
            return "Username contains invalid characters";
        }
        if (!checkLoginExisting(username)){
            return "Username does not exist";
        }
        if (!checkPwdLength(password)){
            return "Password does not match length requirement";
        }
        if (!checkPwdForValidCharacters(password)){
            return "Password contains invalid characters";
        }
        if (passwordCheck(username, password)){
            return "Login successful";
        }
        return "Password does not match users";
    }

}
