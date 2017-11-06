import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class TimeKeeperTest {

    private TimeKeeper timeKeeper;

    public TimeKeeperTest() {
        timeKeeper = new TimeKeeper();
    }


    @Test
    public void getCurrentTime() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        now.format(formatter);
        assertEquals(LocalTime.from(now).getMinute(), timeKeeper.getCurrentTime().getMinute());
    }


    //Both results should be rounded to the same time
    @Test
    public void getCurrentDateTimeString() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        assertEquals(now.format(formatter), timeKeeper.getCurrentDateTimeString());
    }
}