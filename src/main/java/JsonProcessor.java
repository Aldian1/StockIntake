
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JsonProcessor {

    public JSONObject convertReponseToJSON(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(data);
    }

    public ArrayList<String> extractValues(JSONObject data) {
        TimeKeeper timeKeeper = new TimeKeeper();
        ArrayList<String> values = new ArrayList<>();

        String currentTime = timeKeeper.getCurrentDateTime();
        currentTime += ":00";


        JSONObject parent = (JSONObject) data.get("Time Series (1min)");
        System.out.println("\nCurrent time in New York: " + currentTime);
        System.out.println(parent);
        System.out.println(currentTime);
        JSONObject timeUnit = (JSONObject) parent.get(currentTime);

        String r = (String) timeUnit.get("4. close");
        System.out.println("Current value of Snap Stock: " + r);
        return values;
    }
}
