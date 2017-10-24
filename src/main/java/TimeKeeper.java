import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeKeeper {

    public TimeKeeper() {
    }


    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(formatter); }
}
