package com.wellsfletcher.qarth.util.time.schedule.advancedschedule;

import com.wellsfletcher.qarth.util.time.advancedschedule.Event;
import com.wellsfletcher.qarth.util.time.advancedschedule.IntervalMap;
import com.wellsfletcher.qarth.util.time.pattern.IntervalPattern;
import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import com.wellsfletcher.qarth.util.time.schedule.Interval;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class IntervalMapTest {
    private static final int TIMEOUT = 200;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() {

    }

    /**
     * Tests includes method.
     */
    @Test(timeout = TIMEOUT)
    public void testSetNormal() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 5, "First");
        map.set(7, 8, "Second");
        map.set(9, 10, "Third");


        expected += "First";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "Third";

        actual += map.get(2);
        actual += ", ";
        actual += map.get(7);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testSetNoGaps() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 5, "First");
        map.set(5, 9, "Second");
        map.set(9, 10, "Third");


        expected += "First";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "Third";

        actual += map.get(1);
        actual += ", ";
        actual += map.get(5);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testSetWithOverlap() {

    }

    @Test(timeout = TIMEOUT)
    public void testSetWithDuplicatesAndCovers() {

    }

    @Test(timeout = TIMEOUT)
    public void testSetWithZeroWidth() {

    }

    @Test(timeout = TIMEOUT)
    public void testSetWithNestedIntervals() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 20, "First");
        map.set(5, 12, "Second");
        map.set(8, 10, "Third");


        expected += "First";
        expected += ", ";
        expected += "First";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "Third";
        expected += ", ";
        expected += "Third";

        actual += map.get(2);
        actual += ", ";
        actual += map.get(19);
        actual += ", ";
        actual += map.get(6);
        actual += ", ";
        actual += map.get(10);
        actual += ", ";
        actual += map.get(8);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testSetOutOfBounds() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 4, "First");
        map.set(6, 9, "Second");
        map.set(9, 10, "Third");


        expected += "null";
        expected += ", ";
        expected += "null";
        expected += ", ";
        expected += "null";
        expected += ", ";
        expected += "null";

        actual += map.get(0);
        actual += ", ";
        actual += map.get(5);
        actual += ", ";
        actual += map.get(10);
        actual += ", ";
        actual += map.get(49);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstEntry() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 5, "First");
        map.set(7, 8, "Second");
        map.set(9, 10, "Third");

        map.removeFirstEntry();

        expected += "null";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "Third";

        actual += map.get(2);
        actual += ", ";
        actual += map.get(7);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAllValues() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        String valueToRemove = "First";
        map.set(1, 5, valueToRemove);
        map.set(7, 8, "Second");
        map.set(9, 10, valueToRemove);

        map.removeValue(valueToRemove);

        expected += "null";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "null";

        actual += map.get(2);
        actual += ", ";
        actual += map.get(7);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFirstWithNestedValues() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        String valueToRemove = "First";
        map.set(1, 10, valueToRemove);
        map.set(7, 8, "Second");
        // map.set(9, 10, valueToRemove);

        map.removeFirstEntry();

        expected += "null";
        expected += ", ";
        expected += "Second";
        expected += ", ";
        expected += "null";

        actual += map.get(2);
        actual += ", ";
        actual += map.get(7);
        actual += ", ";
        actual += map.get(9);

        assertEquals(expected, actual);
    }

    @Test(timeout = TIMEOUT)
    public void testGetFirstEntry() {
        String expected = "";
        String actual = "";

        IntervalMap<String> map = new IntervalMap<String>();

        // TemporalExpression expression = new IntervalPattern(new Interval(start, end));
        // boolean result = expression.includes(now);
        map.set(1, 5, "First");
        map.set(7, 8, "Second");
        map.set(9, 10, "Third");

        expected += "First";

        actual += map.firstEntry();

        assertEquals(expected, actual);
    }
}
