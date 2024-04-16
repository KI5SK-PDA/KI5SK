package user.controller;

import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import user.view.UserFrame;
import user.view.MainFrame;

public class MainController {
    private UserDAO userDAO;
    private UserFrame userFrame;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;
    private MainFrame mainFrame;

    public MainController() {
        this.userDAO = UserDAOImpl.getInstance();
        this.userFrame = new UserFrame();
        this.signUpController = new SignUpController(userDAO);
        this.loginController = new LoginController(userDAO);
        this.logoutController = new LogoutController();

        this.mainFrame = new MainFrame(userFrame, signUpController, loginController, logoutController);
    }

    public void start() {
        mainFrame.setVisible(true);
    }
}
