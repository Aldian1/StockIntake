package Deperecated;

import org.json.simple.JSONObject;

import java.util.Scanner;

public class UserInput {

    //TODO: Check user input against symbols and currencies
    public String ReadInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String validateInput(String message) {
        return message;
    }
}
