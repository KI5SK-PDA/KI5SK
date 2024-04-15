package user.controller;

import user.dao.UserDAO;
import user.model.User;
import user.model.UserSession;
import user.view.UserInputHandler;

public class LoginController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;

    public LoginController(UserDAO userDAO, UserInputHandler userInputHandler) {
        this.userDAO = userDAO;
        this.userInputHandler = userInputHandler;
    }

    public void login() {
        if (UserSession.getUser() != null) {
            System.out.println("already logged in.");
            return;
        }

        String uid = userInputHandler.getValidInput("id:");
        if (uid == null) return;

        String upw = userInputHandler.getValidInput("pw:");
        if (upw == null) return;

        User user = userDAO.login(uid, upw);

        if (user != null) {
            UserSession.setUser(user);
            System.out.println(UserSession.getUser().getUname() + " welcome!");
        } else {
            System.out.println("login failure.");
        }
    }
}
