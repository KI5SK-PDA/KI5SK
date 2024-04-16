package user.view;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import user.dao.UserDAO;
import user.dao.UserDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserView userView;
    private UserInputHandler userInputHandler;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;

    public MainFrame(UserView userView, UserInputHandler userInputHandler, SignUpController signUpController, LoginController loginController, LogoutController logoutController) {
        this.userView = userView;
        this.userInputHandler = userInputHandler;
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
        JButton exitButton = new JButton("Exit");

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userView.performSignUp(signUpController);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userView.performLogin(loginController);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userView.performLogout(logoutController);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainMenuPanel.add(signUpButton);
        mainMenuPanel.add(loginButton);
        mainMenuPanel.add(logoutButton);
        mainMenuPanel.add(exitButton);

        cardPanel.add(mainMenuPanel, "MainMenu");
        add(cardPanel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        UserView userView = new UserView(new UserInputHandler());
        UserDAO userDAO = UserDAOImpl.getInstance();
        SignUpController signUpController = new SignUpController(userDAO);
        LoginController loginController = new LoginController(userDAO);
        LogoutController logoutController = new LogoutController();
        MainFrame frame = new MainFrame(userView, new UserInputHandler(), signUpController, loginController, logoutController);
        frame.setVisible(true);
    }
}
