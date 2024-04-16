package user.controller;

import user.dao.UserDAO;
import user.model.User;
import user.model.UserSession;
import user.view.UserInputHandler;
import user.view.UserView;

public class LoginController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private UserView userView;

    public LoginController(UserDAO userDAO, UserInputHandler userInputHandler, UserView userView) {
        this.userDAO = userDAO;
        this.userInputHandler = userInputHandler;
        this.userView = userView;
    }

    public void login() {
        if (UserSession.getUser() != null) {
            userView.printLoginStatus();
            return;
        }

        String uid = userInputHandler.getValidIdOrPassword("id:");
        if (uid == null) return;

        String upw = userInputHandler.getValidIdOrPassword("pw:");
        if (upw == null) return;

        User user = userDAO.login(uid, upw);

        if (user != null) {
            UserSession.setUser(user);
            userView.printWelcomeMessage(UserSession.getUser().getUname());
        } else {
            userView.printLoginFailure();
        }
    }
}