package user.view;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import user.model.User;
import user.model.UserSession;

public class UserView {
    private UserInputHandler userInputHandler;

    public UserView(UserInputHandler userInputHandler) {
        this.userInputHandler = userInputHandler;
    }

    public void performLogout(LogoutController logoutController) {
        if (UserSession.isLoggedIn()) {
            UserSession.logout();
            printMessage("logged out");
        } else {
            printMessage("log in first");
        }
    }

    public void performSignUp(SignUpController signUpController) {
        String uid = userInputHandler.getValidIdOrPassword("id: ");
        if (uid == null) return;

        if (signUpController.doesUserIdExist(uid)) {
            printMessage("already exists.");
            return;
        }

        String upw = userInputHandler.getValidIdOrPassword("pw: ");
        if (upw == null) return;

        String uname = userInputHandler.getValidInput("name: ");
        if (uname == null) return;

        signUpController.signUp(uid, upw, uname);
        printMessage("sign up complete.");
    }

    public void performLogin(LoginController loginController) {
        if (UserSession.isLoggedIn()) {
            printMessage("Already logged in.");
            return;
        }

        String uid = userInputHandler.getValidIdOrPassword("id: ");
        if (uid == null) return;

        String upw = userInputHandler.getValidIdOrPassword("pw: ");
        if (upw == null) return;

        User user = loginController.login(uid, upw);
        if (user != null) {
            UserSession.setUser(user);
            printMessage("hello! " + user.getUname());
        } else {
            printMessage("login failure");
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
