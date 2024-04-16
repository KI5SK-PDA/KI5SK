//package user.controller;
//
//import user.dao.UserDAO;
//import user.dao.UserDAOImpl;
//import view.user.PopUpFrame;
//import view.user.UserFrame;
//
//public class MainController {
//    private UserDAO userDAO;
//    private PopUpFrame userFrame;
//    private SignUpController signUpController;
//    private LoginController loginController;
//    private LogoutController logoutController;
//    private UserFrame mainFrame;
//
//    public MainController() {
//        this.userDAO = UserDAOImpl.getInstance();
//        this.userFrame = new PopUpFrame();
//        this.signUpController = new SignUpController();
//        this.loginController = new LoginController();
//        this.logoutController = new LogoutController();
//
//        this.mainFrame = new UserFrame(userFrame, signUpController, loginController, logoutController);
//    }
//
//    public void start() {
//        mainFrame.setVisible(true);
//    }
//}
