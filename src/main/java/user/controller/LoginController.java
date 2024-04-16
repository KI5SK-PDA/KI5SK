package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.model.User;
public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = UserDAOImpl.getInstance();
    }

    public User login(String uid, String upw) {
        return userDAO.login(uid, upw);
    }
}
