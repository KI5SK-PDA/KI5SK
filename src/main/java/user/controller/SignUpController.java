package user.controller;

import user.dao.UserDAO;
import user.view.UserInputHandler;
import user.view.UserView;

public class SignUpController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private UserView userView;

    public SignUpController(UserDAO userDAO, UserInputHandler userInputHandler, UserView userView) {
        this.userDAO = userDAO;
        this.userInputHandler = userInputHandler;
        this.userView = userView;
    }

    public void signUp() {
        String uid = userInputHandler.getValidIdOrPassword("id:");
        if (uid == null) return;

        if (userDAO.doesUserIdExist(uid)) {
            userView.printIdAlreadyExists();
            return;
        }

        String upw = userInputHandler.getValidIdOrPassword("pw:");
        if (upw == null) return;

        String uname = userInputHandler.getValidInput("name:");
        if (uname == null) return;

        userDAO.signUp(uid, upw, uname);
        userView.printSignupCompleted();
    }
}