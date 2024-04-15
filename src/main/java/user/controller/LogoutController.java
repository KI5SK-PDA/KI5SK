package user.controller;

import user.model.UserSession;
public class LogoutController {

    public void logout() {
        if (UserSession.getUser() != null) {
            UserSession.setUser(null);
            System.out.println("logout complete.");
        } else {
            System.out.println("Log in First.");
        }
    }
}
