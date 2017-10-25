import java.io.IOException;

public class DisplayHandler implements IDisplayHandler {

    private final String clearCommand = "cls";

    public DisplayHandler() {
    }


    @Override
    public void dataUpdated(Object data) {

    }

    @Override
    public void clearScreen() throws IOException {
        for(int i =0; i < 1000; i++)
        {
            System.out.println("\b");
        }
    }

    @Override
    public void newDisplay() {

    }

    @Override
    public void updateField() {

    }
}
