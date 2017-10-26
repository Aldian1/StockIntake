public class Printer implements IDisplayHandler {

    public void printMessage(String message) {
        System.out.println(Colors.ANSI_WHITE + message);
    }

    public void printSpecialMessage(String message, String color) {
        System.out.println(color + message);
    }


    public void printStockData(double currentValue, double lastValue, String symbol) {
        if (currentValue != 0) {
            if (currentValue >= lastValue) {
                printSpecialMessage(symbol + " Stock: " + currentValue, Colors.ANSI_GREEN);
            } else {//TODO: Change this to a "print bold" method
                printSpecialMessage("\033[1m" + symbol + " Stock: " + currentValue + "\033[0m", Colors.ANSI_RED);
            }

        } else {
            printSpecialMessage(symbol + " offline [stock market not open]", Colors.ANSI_YELLOW);
        }
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
