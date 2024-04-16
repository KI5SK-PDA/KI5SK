package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.view.UserInputHandler;
import user.view.UserView;
import user.view.MainFrame;

public class UserController {
    private UserDAO userDAO;
    private UserInputHandler userInputHandler;
    private UserView userView;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;
    private MainFrame mainFrame;

    public UserController() {
        this.userDAO = UserDAOImpl.getInstance();
        this.userInputHandler = new UserInputHandler();
        this.userView = new UserView(userInputHandler);
        this.signUpController = new SignUpController(userDAO);
        this.loginController = new LoginController(userDAO);
        this.logoutController = new LogoutController();

        this.mainFrame = new MainFrame(userView, userInputHandler, signUpController, loginController, logoutController);
    }

    public void start() {
        mainFrame.setVisible(true);
    }
}
