package user.view;

import user.controller.LoginController;
import user.controller.LogoutController;
import user.controller.SignUpController;
import user.model.User;
import user.model.UserSession;

import javax.swing.*;

public class UserView {

    public UserView() {

    }

    public void performLogout(LogoutController logoutController, JFrame parentFrame) {
        if (UserSession.isLoggedIn()) {
            UserSession.logout();
            JOptionPane.showMessageDialog(parentFrame, "Logged out", "Logout", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Log in first", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void performSignUpGUI(SignUpController signUpController, JFrame parentFrame) {
        JTextField uidField = new JTextField();
        JPasswordField upwField = new JPasswordField();
        JTextField unameField = new JTextField();

        Object[] message = {
                "ID:", uidField,
                "Password:", upwField,
                "Name:", unameField
        };

        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Sign Up", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String uid = uidField.getText().trim();
            String upw = new String(upwField.getPassword()).trim();
            String uname = unameField.getText().trim();

            if (signUpController.doesUserIdExist(uid)) {
                JOptionPane.showMessageDialog(parentFrame, "Already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            signUpController.signUp(uid, upw, uname);
            JOptionPane.showMessageDialog(parentFrame, "Sign up complete.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void performLoginGUI(LoginController loginController, JFrame parentFrame) {
        JTextField uidField = new JTextField();
        JPasswordField upwField = new JPasswordField();

        Object[] message = {
                "ID:", uidField,
                "Password:", upwField
        };

        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String uid = uidField.getText().trim();
            String upw = new String(upwField.getPassword()).trim();

            User user = loginController.login(uid, upw);
            if (user != null) {
                UserSession.setUser(user);
                JOptionPane.showMessageDialog(parentFrame, "Hello! " + user.getUname(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Login failure", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
