package user.view;

public class UserView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printWelcomeMessage(String userName) {
        System.out.println(userName + " welcome!");
    }

    public void printLoginFailure() {
        System.out.println("login failure.");
    }

    public void printLogoutComplete() {
        System.out.println("logout complete.");
    }

    public void printLoginFirst() {
        System.out.println("Log in First.");
    }
    public void printIdAlreadyExists() {
        System.out.println("id already exists");
    }

    public void printSignupCompleted() {
        System.out.println("signup completed.");
    }

    public void printLoginStatus() {
        System.out.println("already logged in.");
    }
}