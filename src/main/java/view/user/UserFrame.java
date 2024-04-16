package view.user;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import user.dao.UserDAO;
import user.dao.UserDAOImpl;
import view.controller.BasicTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PopUpFrame userFrame;
    private SignUpController signUpController;
    private LoginController loginController;
    private LogoutController logoutController;
    private BasicTransition toSelect;

    JButton signUpButton = new JButton("Sign Up");
    JButton loginButton = new JButton("Login");
    JButton logoutButton = new JButton("Logout");
    JButton exitButton = new JButton("Exit");

    public UserFrame(
            PopUpFrame userFrame,
            SignUpController signUpController,
            LoginController loginController,
            LogoutController logoutController,
            BasicTransition toSelect
    ) {
        this.userFrame = userFrame;
        this.signUpController = signUpController;
        this.loginController = loginController;
        this.logoutController = logoutController;
        this.toSelect = toSelect;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("KI5SK SERVICE");

        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainMenuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 100, 15, 100);

        JLabel titleLabel = new JLabel("KI5SK SERVICE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gbc.insets = new Insets(30, 100, 30, 100);
        mainMenuPanel.add(titleLabel, gbc);

        gbc.insets = new Insets(15, 100, 15, 100);

        Font buttonFont = new Font("Arial", Font.BOLD, 28);
        signUpButton.setFont(buttonFont);
        loginButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        Dimension buttonSize = new Dimension(250, 80);
        signUpButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        signUpButton.addActionListener(e -> userFrame.performSignUpGUI(signUpController, this));
        loginButton.addActionListener(e -> userFrame.performLoginGUI(loginController, this, toSelect));
        logoutButton.addActionListener(e -> userFrame.performLogout(logoutController, this));
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuPanel.add(signUpButton, gbc);
        mainMenuPanel.add(loginButton, gbc);
        mainMenuPanel.add(logoutButton, gbc);
        mainMenuPanel.add(exitButton, gbc);

        cardPanel.add(mainMenuPanel, "Main Menu");

        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "Main Menu");


//        setVisible(true);
    }

}
