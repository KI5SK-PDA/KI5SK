package user.controller;

import user.dao.UserDAO;
import user.view.UserInputHandler;

public class SignUpController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;

    public SignUpController(UserDAO userDAO, UserInputHandler userInputHandler) {
        this.userDAO = userDAO;
        this.userInputHandler = userInputHandler;
    }

    public void signUp() {
        String uid = userInputHandler.getValidInput("id:");
        if (uid == null) return;

        if (userDAO.doesUserIdExist(uid)) {
            System.out.println("id already exists");
            return;
        }

        String upw = userInputHandler.getValidInput("pw:");
        if (upw == null) return;

        String uname = userInputHandler.getValidInput("name:");
        if (uname == null) return;

        userDAO.signUp(uid, upw, uname);
        System.out.println("signup completed.");
    }
}
