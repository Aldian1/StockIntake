import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeKeeper {

    public String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        return now.format(formatter);
    }

    public LocalTime getCurrentTime()
    {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        now.format(formatter);
        return LocalTime.from(now);
    }
}