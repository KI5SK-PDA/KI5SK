package user.view;

public class InputValidator {
    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty() && input.trim().length() >= 2;
    }
}
