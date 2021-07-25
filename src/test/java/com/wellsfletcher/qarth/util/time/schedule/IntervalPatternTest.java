package com.wellsfletcher.qarth.util.time.schedule;

import com.wellsfletcher.qarth.util.time.pattern.IntervalPattern;
import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for simple App.
 */
public class IntervalPatternTest {
    private static final int TIMEOUT = 200;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() {

    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhenTrue() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:34:21", formatter);

        TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWithEarlyTime() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:09:21", formatter);

        TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoDuringInterval() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:34:21", formatter);

        TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        LocalDateTime result = (LocalDateTime) expression.adjustInto(now);

        expected += "2020-02-15 01:34:21";
        actual += result.format(formatter);
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoBeforeInterval() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:09:21", formatter);

        TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        LocalDateTime result = (LocalDateTime) expression.adjustInto(now);

        expected += "2020-02-15 01:30:05";
        actual += result.format(formatter);
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoAfterInterval() {
        String expected = "";
        String actual = "";

        LocalDateTime start = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:59:21", formatter);

        TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        LocalDateTime result = (LocalDateTime) expression.adjustInto(now);

        expected += "2020-02-15 01:37:05";
        actual += result.format(formatter);
        assertEquals(expected, actual);
    }
}
