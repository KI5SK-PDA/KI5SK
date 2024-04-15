package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.view.UserInputHandler;

public class UserController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;

    public UserController() {
        this.userDAO = new UserDAOImpl();
        this.userInputHandler = new UserInputHandler();
        this.signUpController = new SignUpController(userDAO, userInputHandler);
        this.loginController = new LoginController(userDAO, userInputHandler);
        this.logoutController = new LogoutController();
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
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("please select again.");
                    break;
            }
        }
    }
}
