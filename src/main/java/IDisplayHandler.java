import java.io.IOException;

public interface IDisplayHandler {

void dataUpdated(Object data);
void clearScreen() throws IOException;
void newDisplay();
void updateField();
}
