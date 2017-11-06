import org.junit.Test;
import static org.junit.Assert.*;

public class UserInputTest {
    private UserInput userInput;

    public UserInputTest() {
        userInput = new UserInput();
    }

    @Test
    public void readUserInput() throws Exception {

    }

    @Test
    public void validateUserInputSuccess() throws Exception {
        assertTrue(userInput.validateUserInput("Hell this will pass"));
    }

    @Test
    public void validateUserInputFailure() throws Exception {
        assertFalse(userInput.validateUserInput("a"));
    }
}