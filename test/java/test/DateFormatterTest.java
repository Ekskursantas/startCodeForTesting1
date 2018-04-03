package test;

import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;

import testex.DateFormatter;
import testex.IDateFormatter;
import testex.JokeException;

import static org.junit.Assert.*;

public class DateFormatterTest {

    private IDateFormatter df;

    public DateFormatterTest() {
    }

    @Before
    public void setup() {
        df = new DateFormatter();
    }

    @Test
    public void testGetFormattedDateCph() throws Exception {
        assertThat(df.getFormattedDate("Europe/Copenhagen", new Date(2018 - 1900, 0, 25, 05, 00)), is("25 Jan 2018 05:00 AM"));
    }
    
    @Test
    public void testGetFormattedDateMnl() throws Exception {
        assertThat(df.getFormattedDate("Europe/Vilnius", new Date(2018 - 1900, 5, 30, 23, 01)), is("01 Jul 2018 12:01 AM"));
    }

    @Test(expected = JokeException.class)
    public void testJokeException() throws JokeException {
        df.getFormattedDate("illegal time zone", new Date());
    }
}
