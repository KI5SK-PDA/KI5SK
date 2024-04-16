package user.controller;

import user.model.UserSession;

public class LogoutController {

    public boolean logout() {
        if (UserSession.getUser() != null) {
            UserSession.setUser(null);
            return true;
        } else {
            return false;
        }
    }
}
