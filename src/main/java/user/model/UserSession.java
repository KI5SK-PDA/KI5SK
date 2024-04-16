package user.model;

public class UserSession {
    private static User user = null;

    public static boolean isLoggedIn() {
        return user != null;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }

    public static User getUser() {
        return user;
    }

    public static void logout() {
        user = null;
    }
}
