package user.view;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import user.dao.UserDAO;
import user.dao.UserDAOImpl;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserView userView;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;

    public MainFrame(UserView userView, SignUpController signUpController, LoginController loginController, LogoutController logoutController) {
        this.userView = userView;
        this.signUpController = signUpController;
        this.loginController = loginController;
        this.logoutController = logoutController;

        initializeUI();
    }

    private void initializeUI() {
        setTitle("User Management System");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(1, 4));
        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");
        JButton logoutButton = new JButton("Logout");
        JButton exitButton =new JButton("exit");

        signUpButton.addActionListener(e -> userView.performSignUpGUI(signUpController, this));
        loginButton.addActionListener(e -> userView.performLoginGUI(loginController, this));
        logoutButton.addActionListener(e -> userView.performLogout(logoutController, this));
        exitButton.addActionListener(e -> System.exit(0));


        mainMenuPanel.add(signUpButton);
        mainMenuPanel.add(loginButton);
        mainMenuPanel.add(logoutButton);
        mainMenuPanel.add(exitButton);


        cardPanel.add(mainMenuPanel, "Main Menu");


        add(cardPanel, BorderLayout.CENTER);


        cardLayout.show(cardPanel, "Main Menu");


        setVisible(true);
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        SignUpController signUpController = new SignUpController(userDAO);
        LoginController loginController = new LoginController(userDAO);
        LogoutController logoutController = new LogoutController();

        UserView userView = new UserView();
        new MainFrame(userView, signUpController, loginController, logoutController);
    }
}
