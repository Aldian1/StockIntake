public class Printer implements IDisplayHandler {

    public void printMessage(String message) {
        System.out.println(Colors.ANSI_WHITE + message);
    }

    public void printSpecialMessage(String message, String color) {
        System.out.println(color + message);
    }


    @Override
    public void dataUpdated(Object data) {

    }

    @Override
    public void clearScreen() {

    }

    @Override
    public void newDisplay() {

    }

    @Override
    public void updateField() {

    }
}
