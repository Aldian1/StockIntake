import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
    private ApiAccessor apiAccessor;
    private JsonProcessor jsonProcessor;
    private UserInput userInput;
    private Printer printer;
    private TimeKeeper timeKeeper;
    private String mockMessageURL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=SNAP&interval=1min&";
    private final String apikKey = "apikey=IYI4AYG8YGKDQEGB";

    public Main() {
        apiAccessor = new ApiAccessor();
        jsonProcessor = new JsonProcessor();
        userInput = new UserInput();
        printer = new Printer();
        timeKeeper = new TimeKeeper();
        onEntry();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }

    private void onEntry() {
        String response = "";
        JSONObject responseConverted = new JSONObject();

        printer.printMessage("Please enter the symbol (BTC, ETC)");
        String marketSymbol = "symbol=" + "MSFT";
        printer.printMessage("Please enter the currency (GBP, USD)");
        String marketCurrency = "market=" + "USD";
        mockMessageURL = mockMessageURL +  apikKey;
        int limiter = 0;

        while (responseConverted.size() < 2 && limiter < 200) {
            try {
                limiter++;
                System.out.println("Querying URL: " + mockMessageURL);
                response = apiAccessor.getTargetBody(mockMessageURL);
                try {
                    for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
                        updateProgress(progressPercentage);
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) {}
                responseConverted = jsonProcessor.convertReponseToJSON(response);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            if (responseConverted != null) {
                //System.out.println(responseConverted.get("Meta Data"));
                ArrayList<String> requestHighs = jsonProcessor.extractValues(responseConverted);
                System.out.println(requestHighs);
            }
        }
    }

    static void updateProgress(double progressPercentage) {
        final int width = 50; // progress bar width in chars

        System.out.print("\r[");
        int i = 0;
        for (; i <= (int)(progressPercentage*width); i++) {
            System.out.print(".");
        }
        for (; i < width; i++) {
            System.out.print(" ");
        }
        System.out.print("]");
    }
}

///STOCK CHECKER THAT ALLOWS YOU TO RUN COMMANDS IN THE COMMAND LINE
// AND OPEN UP STOCK VIA A COMMAND SUCH AS [check bitcoin today] ==
// Displays todays bitcoin value [compare bitcoin [date] [date]]

//https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&apikey=IYI4AYG8YGKDQEGB

//https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_INTRADAY&symbol=BTC&market=GBP&apikey=IYI4AYG8YGKDQEGB