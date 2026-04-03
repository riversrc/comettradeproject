public class LogoutGUI {
    private AuthController controller = new AuthController();

    public void clickLogout(String userId) {
        String msg = controller.logout(userId);
        System.out.println(msg);

        // redirect to login after cameron is done with it something like this:
        // LoginGUI loginGUI = new LoginGUI();
        // loginGUI.show();
    }
}