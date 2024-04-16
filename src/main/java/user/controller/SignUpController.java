package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;

public class SignUpController {
    private UserDAO userDAO;

    public SignUpController() {
        this.userDAO = UserDAOImpl.getInstance();
    }

    public boolean doesUserIdExist(String uid) {
        return userDAO.doesUserIdExist(uid);
    }

    public void signUp(String uid, String upw, String uname) {
        userDAO.signUp(uid, upw, uname);
    }
}
