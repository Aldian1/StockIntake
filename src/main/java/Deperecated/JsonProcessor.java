package Deperecated;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalTime;


public class JsonProcessor {

    private JSONObject currentOpen;
    private DataHolder dh;

    public JsonProcessor() {
        currentOpen = new JSONObject();
        dh = new DataHolder();
    }


    public JSONObject convertReponseToJSON(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(data);
    }

    public JSONArray extractValues(JSONObject data) {
        //TimeKeeper timeKeeper = new TimeKeeper;
        //String currentTime = timeKeeper.getCurrentDateTimeString();
        String currentTime = ":00";

        JSONObject parent = (JSONObject) data.get("Time Series (1min)");

        String[] currentTimeSplit = currentTime.split("\\s+");
        LocalTime editableTime = LocalTime.parse(currentTimeSplit[1]);
        StringBuilder sb = new StringBuilder();
        JSONArray timeValues = new JSONArray();


        Integer threeHours = 60 * 3;
        for (int i = 0; i < threeHours; i++) {
            sb.delete(0, sb.length());
            editableTime = editableTime.minusMinutes(1);
            currentTimeSplit[1] = editableTime.toString();
            sb.insert(0, currentTimeSplit[1]);
            sb.insert(0, currentTimeSplit[0] + " ");
            sb.append(":00");
            JSONObject j = (JSONObject) parent.get(sb.toString());
            if(j != null) {
                dh.setCurrentOpen(sb.toString(), j.get("1. open").toString());
            }
        }

        System.out.println("Current Open Object: ");
        System.out.println(dh.getCurrentOpen());
        return timeValues;
    }
}
