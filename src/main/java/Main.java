import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.time.LocalTime;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
    private Printer printer;
    private TimeKeeper timeKeeper;
    private DisplayHandler displayHandler;
    private String[] mockMessageURL = new String[2];
    private final String apikKey = "apikey=IYI4AYG8YGKDQEGB";
    private Map<String, Double> portfolio = new HashMap<String, Double>();

    public Main() {
        printer = new Printer();
        timeKeeper = new TimeKeeper();
        displayHandler = new DisplayHandler();
        //default stock
        mockMessageURL[0] = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=";
        mockMessageURL[1] = "&interval=1min&";
        onEntry();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }

    //TODO: Use list not array
    //TODO: Use string builder to construct the message
    //TODO: Use string builder to construct the url
    //TODO: Convert this into a command so the user can dynamically change there portfolio

    private void onEntry() {
        printer.printMessage("Getting latest data");
        LocalTime lastTimeStamp = timeKeeper.getCurrentTime();

        portfolio.put("SNAP", 0D);
        portfolio.put("FB", 0D);
        portfolio.put("GOOGL", 0D);
        portfolio.put("BT.A", 0D);

        while (true) {
            printer.printMessage("Last update was at : " + timeKeeper.getCurrentDateTimeString());
            printer.printMessage("Current tracked portfolio: ");
            for (String key : portfolio.keySet()) {
                try {
                    String finalizedURL = String.format("%s%s%s%s", mockMessageURL[0], key, mockMessageURL[1], apikKey);
                    String latestData = getLatestDataFromURL(finalizedURL);
                    double currentValue = doDataConversions(latestData, key);
                    printer.printStockData(currentValue, portfolio.get(key), key);
                    portfolio.put(key, currentValue);
                } catch (ParseException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private double doDataConversions(String latestData, String symbol) throws IOException, ParseException, InterruptedException {
        return extractData(latestData, symbol);
    }

    private double extractData(String latestData, String symbol) throws ParseException, InterruptedException {
        if (latestData.length() > 4) {
            JSONParser parser = new JSONParser();
            JSONObject convertedInput = (JSONObject) parser.parse(latestData);
            JSONObject timeSeriesObj = (JSONObject) convertedInput.get("Time Series (1min)");
            JSONObject mostRecentEntry = (JSONObject) timeSeriesObj.get(timeKeeper.getCurrentDateTimeString());
            try {
                String s = mostRecentEntry.get("4. close").toString();
                return Double.parseDouble(s);
            } catch (Exception e) {
                return portfolio.get(symbol);
            }
        }
        return portfolio.get(symbol);
    }

    private String getLatestDataFromURL(String URL) throws IOException {
        InputStream is = null;
        try {
            URL ur = null;
            ur = new URL(URL);
            URLConnection conn = ur.openConnection();
            is = conn.getInputStream();
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }
}

