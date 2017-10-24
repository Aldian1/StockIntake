import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.junit.Assert.*;

public class TimeKeeperTest {

    private TimeKeeper timeKeeper;
    public TimeKeeperTest() {
    timeKeeper = new TimeKeeper();
    }

    //Both results should be rounded to the same time
    @Test
    public void getCurrentDateTime() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        assertEquals(LocalDateTime.now().format(formatter),timeKeeper.getCurrentDateTime().format(formatter));
    }
}