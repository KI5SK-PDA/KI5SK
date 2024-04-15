package user.dao;

import user.model.User;
import java.util.HashMap;

public class UserDAOImpl implements UserDAO {
    private HashMap<String, User> userMap = new HashMap<>();

    @Override
    public void signUp(String uid, String upw, String uname) {
        User newUser = new User(uid, upw, uname);
        userMap.put(uid, newUser);
    }

    @Override
    public User login(String uid, String upw) {
        User user = userMap.get(uid);
        if (user != null && user.getUpw().equals(upw)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean doesUserIdExist(String uid) {
        return userMap.containsKey(uid);
    }
}
