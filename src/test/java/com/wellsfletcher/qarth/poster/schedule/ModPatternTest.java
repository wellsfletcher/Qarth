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
public class ModPatternTest {
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

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-16 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesValueWhenTrue() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-17 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesValueWhenFalse() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-16 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesModuloWhenTrue() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 7;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-24 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesModuloWhenFalse() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 7;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-25 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhenTrue2() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-16 01:28:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesDayZeroLowerBoundary() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 00:00:01", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesDayMinusOneUpperBoundary() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-14 23:59:59", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhenEarly() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesWhenLate() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now  = LocalDateTime.parse("2020-02-17 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesDayOneLowerBoundary() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-16 00:00:01", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesDayZeroUpperBoundary() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 23:59:59", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testIncludesDayZeroUpperBoundarySoft() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 22:59:03", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testInBetweenRepetitions() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-19 00:00:01", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testSecondRepetitionWhenEarlyTrue() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-21 00:00:01", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testSecondRepetitionWhenLateTrue() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-21 23:59:59", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "true";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testSecondRepetitionWhenEarlyFalse() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now   = LocalDateTime.parse("2020-02-20 23:59:59", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testSecondRepetitionWhenLateFalse() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 1;
        LocalDateTime now  = LocalDateTime.parse("2020-02-22 00:00:01", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        boolean result = expression.includes(now);

        expected += "false";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValueNegativeSecondRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-04 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-07 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValueNegativeFirstRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-09 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-12 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }


    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValueFirstRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-15 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-17 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValueSecondRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-19 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-22 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }

    /**
     * Tests adjustInto method.
     */
    @Test(timeout = TIMEOUT)
    public void testAdjustIntoValueThirdRepetition() {
        String expected = "";
        String actual = "";

        LocalDateTime root = LocalDateTime.parse("2020-02-15 01:30:05", formatter);
        // LocalDateTime end   = LocalDateTime.parse("2020-02-15 01:37:05", formatter);
        Integer modulo = 5;
        Integer value = 2;
        LocalDateTime now   = LocalDateTime.parse("2020-02-24 01:34:21", formatter);

        TemporalExpression expression = new ModPattern(root, modulo, value);
        String result = formatter.format(expression.adjustInto(now));

        expected += "2020-02-27 00:00:00";
        actual += result;
        assertEquals(expected, actual);
    }
}
