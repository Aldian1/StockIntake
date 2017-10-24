import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonProcessorTest {

    private JsonProcessor jsonProcessor;

    public JsonProcessorTest() {
        jsonProcessor = new JsonProcessor();
    }

    @Test
    public void convertReponseToJSON() throws Exception {
        String expectedResult = "{\"name\":\"John\"}";
        JSONParser jsonParser = new JSONParser();
        assertEquals(jsonParser.parse(expectedResult), jsonProcessor.convertReponseToJSON("{\"name\":\"John\"}"));
    }

    @Test
    public void extractSymbolPrice() throws Exception {
    }

    @Test
    public void extractDailyPrice() throws Exception {

    }

}