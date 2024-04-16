package user.dao;

import user.model.User;

public interface UserDAO {
    void signUp(String uid, String upw, String uname);
    User login(String uid, String upw);
    boolean doesUserIdExist(String uid);
}
