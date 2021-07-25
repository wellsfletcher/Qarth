package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.*;
import java.time.temporal.*;

/**
 * Represents TemporalExpression.
 */
public class IntervalPattern implements TemporalExpression {
    Interval interval;

    public IntervalPattern(Interval interval) {
        this.interval = interval;
    }

    public Temporal adjustInto(Temporal temporal) {
        // can either make it return null if the event has already occurred
        // or can make it always return the even
        // should return the start date, or return the inputted date if the event is actively occurring
        LocalDateTime date = LocalDateTime.from(temporal);
        LocalDateTime result = date;

        /*
        // int comparison = start.compareTo(date);
        if (start.compareTo(date) > 0) {
            result = start;
        } else if (end.compareTo(date) < 0) {
            result = end; // the result could equal null or a past date here
            // result = null;
        }
        */
        if (interval.isAfter(date)) {
            result = interval.getStart();
        } else if (interval.isBefore(date)) {
            result = interval.getEnd();
        }

        return result;
    }
}
