package user.view;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;

public class MainView {
    private UserView userView;
    private UserInputHandler userInputHandler;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;

    public MainView(UserView userView, UserInputHandler userInputHandler, SignUpController signUpController, LoginController loginController, LogoutController logoutController) {
        this.userView = userView;
        this.userInputHandler = userInputHandler;
        this.signUpController = signUpController;
        this.loginController = loginController;
        this.logoutController = logoutController;
    }

    public void start() {
        while (true) {
            String choice = userInputHandler.getUserChoice();

            switch (choice) {
                case "1":
                    userView.performSignUp(signUpController);
                    break;
                case "2":
                    userView.performLogin(loginController);
                    break;
                case "3":
                    userView.performLogout(logoutController);
                    break;
                case "0":
                    userView.printMessage("exit");
                    return;
                default:
                    userView.printMessage("select again");
                    break;
            }
        }
    }
}
