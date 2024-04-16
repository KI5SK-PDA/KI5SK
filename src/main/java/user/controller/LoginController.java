package user.controller;

import user.dao.UserDAO;
import user.model.User;
public class LoginController {
    private UserDAO userDAO;

    public LoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String uid, String upw) {
        return userDAO.login(uid, upw);
    }
}
