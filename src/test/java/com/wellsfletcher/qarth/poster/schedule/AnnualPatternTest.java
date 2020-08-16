package com.wellsfletcher.qarth.poster.schedule;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.*;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for simple App.
 */
public class AnnualPatternTest {
    private static final int TIMEOUT = 200;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() {

    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhenFalse() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-13 01:34:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhen1stRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:35:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhen2ndRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2021-02-14 01:35:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhen3rdRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2022-02-14 01:35:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesInBetweenRepetitions() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2021-02-15 01:35:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValue1stRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-11 01:34:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-15 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValue2ndRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2020-02-19 01:34:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2021-02-14 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValue3rdRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        LocalDateTime now   = LocalDateTime.parse("2021-02-19 01:34:21", formatter);

        TemporalExpression expression = new AnnualPattern(root);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2022-02-14 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }
}
