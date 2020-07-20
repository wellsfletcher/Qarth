package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class IntervalPattern implements TemporalExpression {
    protected LocalDateTime start;
    protected Duration duration;

    // public IntervalPattern(Instant start, Duration duration) {
    public IntervalPattern(LocalDateTime start, Duration duration) {
        this.start = start;
        this.duration = duration;
    }

    public Temporal adjustInto(Temporal temporal) {
        // can either make it return null if the event has already occurred
        // or can make it always return the even
        // should return the start date, or return the inputted date if the event is actively occurring
        LocalDateTime date = LocalDateTime.from(temporal);
        LocalDateTime end = getEndDate();
        LocalDateTime result = date;

        // int comparison = start.compareTo(date);
        if (start.compareTo(date) > 0) {
            result = start;
        } else if (end.compareTo(date) < 0) {
            result = end; // the result could equal null or a past date here
            // result = null;
        }

        return result;
    }

    public LocalDateTime getEndDate() {
        LocalDateTime end = start.plus(duration);
        return end;

    }
}
