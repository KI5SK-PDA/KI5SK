package view.user;

public class InputValidator {
    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty() && input.trim().length() >= 2;
    }

    public static boolean isValidIdOrPassword(String input) {
        return input != null && !input.trim().isEmpty() && input.trim().length() >= 4;
    }

}
