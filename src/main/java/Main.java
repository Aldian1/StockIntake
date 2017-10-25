import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
    Map<String, Double> portfolio = new HashMap<String, Double>();

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

    private void onEntry() {
        printer.printMessage("Getting latest data");
        double lastValue = 0;
        LocalTime lastTimeStamp = timeKeeper.getCurrentTime();
        //TODO: Convert this into a command so the user can dynamically change there portfolio
        portfolio.put("SNAP", (double) 0);
        portfolio.put("FB", (double) 0);
        portfolio.put("GOOGL", (double) 0);
        List<String> keys = new ArrayList<>(portfolio.keySet());
        while (true) {
            printer.printMessage("Last update was at : " + timeKeeper.getCurrentDateTimeString());
            printer.printMessage("Current tracked portfolio: ");
            for (int i = 0; i < portfolio.size(); i++) {
                try {
                    String latestData = getLatestDataFromURL(mockMessageURL[0] + keys.get(i) + mockMessageURL[1] + apikKey);
                    double currentValue = doDataConversions(latestData, keys.get(i));
                    printMessages(currentValue, lastValue, keys.get(i));
                    Thread.sleep(1000);
                } catch (ParseException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private double doDataConversions(String latestData, String symbol) throws IOException, ParseException, InterruptedException {
        return extractData(latestData, symbol);
    }

    private void printMessages(double currentValue, double lastValue, String symbol) {
        if (currentValue != 0) {
            if (currentValue >= portfolio.get(symbol)) {
                printer.printSpecialMessage(symbol + " Stock: " + currentValue, Colors.ANSI_GREEN);
            } else {
                printer.printSpecialMessage("\033[1m" + symbol + " Stock: " + currentValue + "\033[0m", Colors.ANSI_RED);
            }
            portfolio.put(symbol, currentValue);
        }
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
        URL ur = null;
        ur = new URL(URL);
        URLConnection conn = ur.openConnection();
        InputStream is = conn.getInputStream();
        return new Scanner(is).useDelimiter("\\A").next();
    }
}

