package user.controller;

import user.dao.UserDAO;

public class SignUpController {
    private UserDAO userDAO;

    public SignUpController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean doesUserIdExist(String uid) {
        return userDAO.doesUserIdExist(uid);
    }

    public void signUp(String uid, String upw, String uname) {
        userDAO.signUp(uid, upw, uname);
    }
}
