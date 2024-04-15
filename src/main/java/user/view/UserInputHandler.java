package user.view;

import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner = new Scanner(System.in);

    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public String getUserChoice() {
        System.out.println("1. signup / 2. login / 3. logout / 0. exit");
        return scanner.nextLine();
    }
    public String getValidInput(String prompt) {
        String input = getInput(prompt);
        if (!InputValidator.isValidInput(input)) {
            System.out.println("invalid input");
            return null;
        }
        return input;
    }
}
