package user.controller;

import user.model.UserSession;
import user.view.UserView;

public class LogoutController {
    private UserView userView;

    public LogoutController(UserView userView) {
        this.userView = userView;
    }

    public void logout() {
        if (UserSession.getUser() != null) {
            UserSession.setUser(null);
            userView.printLogoutComplete();
        } else {
            userView.printLoginFirst();
        }
    }
}