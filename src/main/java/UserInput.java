import java.io.InputStream;
import java.util.Scanner;

public class UserInput {

    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    boolean validateUserInput(String input) {
        if (input.length() < 2) {
            return false;
        } else if (input.contains("! ? @ # $ % ^ & *")) {
            return false;
        }
        return true;
    }
}
