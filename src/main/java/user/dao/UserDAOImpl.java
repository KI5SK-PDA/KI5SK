package user.dao;

import user.model.User;
import java.util.HashMap;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    private HashMap<String, User> userMap = new HashMap<>(){{
        put("abcd", new User("abcd", "1234","ki5sk")); // test 용
    }};

    public UserDAOImpl() {}
    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

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
