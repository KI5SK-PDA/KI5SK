package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.view.UserInputHandler;
import user.view.UserView;

public class UserController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private UserView userView;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;

    public UserController() {
        this.userDAO = new UserDAOImpl();
        this.userInputHandler = new UserInputHandler();
        this.userView = new UserView();
        this.signUpController = new SignUpController(userDAO, userInputHandler, userView);
        this.loginController = new LoginController(userDAO, userInputHandler, userView);
        this.logoutController = new LogoutController(userView);
    }

    public void start() {
        while (true) {
            String choice = userInputHandler.getUserChoice();

            switch (choice) {
                case "1":
                    signUpController.signUp();
                    break;
                case "2":
                    loginController.login();
                    break;
                case "3":
                    logoutController.logout();
                    break;
                case "0":
                    userView.printMessage("Exiting program...");
                    return;
                default:
                    userView.printMessage("please select again.");
                    break;
            }
        }
    }
}
