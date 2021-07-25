package com.wellsfletcher.qarth.util.time.schedule.advancedschedule;

import com.wellsfletcher.qarth.util.time.advancedschedule.Event;
import com.wellsfletcher.qarth.util.time.advancedschedule.EventManager;
import com.wellsfletcher.qarth.util.time.advancedschedule.RepeatingEvent;
import com.wellsfletcher.qarth.util.time.pattern.IntervalPattern;
import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import com.wellsfletcher.qarth.util.time.schedule.Interval;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class EventManagerTest {
    private static final int TIMEOUT = 200;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() {

    }

    /**
     * Tests includes method.
     */
    /*
    @Test(timeout = TIMEOUT)
    public void testRun() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2021-08-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2021-08-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2021-08-15 01:34:21", formatter);

        Event a = new Event(() -> System.out.println("action"), new Interval(start, end));

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        EventManager schedule = new EventManager();
        schedule.add(a);


        expected += "true";
        actual += "result";
        assertEquals(expected, actual);
    }
     */

    // @Test(timeout = 30000)
    public void testRun() {
        String expected = "";
        String actual = "";

        LocalDateTime now   = LocalDateTime.now();
        LocalDateTime start;
        LocalDateTime end;

        start = now.plusSeconds(5);
        end   = now.plusSeconds(10);
        Event a = new Event(() -> System.out.println("action"), new Interval(start, end));

        start = now.plusSeconds(15);
        end   = now.plusSeconds(19);
        Event b = new Event(() -> System.out.println("action"), new Interval(start, end));

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        EventManager schedule = new EventManager();
        schedule.add(a);
        schedule.add(b);

        schedule.run();

        expected += "true";
        actual += "true";
        assertEquals(expected, actual);
    }

    // @Test(timeout = 50000)
    public void testRunWithRepeatingEvent() {
        String expected = "";
        String actual = "";

        LocalDateTime now   = LocalDateTime.now();
        LocalDateTime start;
        LocalDateTime end;
        Duration duration;

        start = now.plusSeconds(4);
        end   = now.plusSeconds(6);
        duration = Duration.ofSeconds(4);
        Event a = new RepeatingEvent(() -> System.out.println("action = a"), new Interval(start, end), duration);

        start = now.plusSeconds(15);
        end   = now.plusSeconds(19);
        Event b = new Event(() -> System.out.println("action = b"), new Interval(start, end));

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        EventManager schedule = new EventManager();
        schedule.add(a);
        schedule.add(b);

        schedule.run();

        expected += "true";
        actual += "true";
        assertEquals(expected, actual);
    }

    //- @Test(timeout = 40000)
    public void testRunWithFastRepeatingEvent() {
        String expected = "";
        String actual = "";

        LocalDateTime now   = LocalDateTime.now();
        LocalDateTime start;
        LocalDateTime end;
        Duration duration;

        start = now.plusSeconds(4);
        end   = now.plusSeconds(5);
        duration = Duration.ofSeconds(2);
        Event a = new RepeatingEvent(() -> System.out.println("action = a"), new Interval(start, end), duration);

        start = now.plusSeconds(12);
        end   = now.plusSeconds(19);
        Event b = new Event(() -> System.out.println("action = b"), new Interval(start, end));

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        EventManager schedule = new EventManager();
        schedule.add(a);
        schedule.add(b);

        schedule.run();

        expected += "true";
        actual += "true";
        assertEquals(expected, actual);
    }
}
