package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.view.UserInputHandler;
import user.view.UserView;
import user.view.MainView;

public class UserController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private UserView userView;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;
    private MainView mainView;

    public UserController() {
        this.userDAO = UserDAOImpl.getInstance();
        this.userInputHandler = new UserInputHandler();
        this.userView = new UserView(userInputHandler);
        this.signUpController = new SignUpController(userDAO);
        this.loginController = new LoginController(userDAO);
        this.logoutController = new LogoutController();

        this.mainView = new MainView(userView, userInputHandler, signUpController, loginController, logoutController);
    }

    public void start() {
        mainView.start();
    }
}
