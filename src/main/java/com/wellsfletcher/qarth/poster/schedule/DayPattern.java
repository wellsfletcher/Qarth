package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public abstract class DayPattern extends IntervalPattern {
    public DayPattern(LocalDateTime day) {
        super(day(day));
    }

    protected static Interval day(LocalDateTime day) {
        return new Interval(floor(day), ceiling(day));
    }

    /**
     * Rounds the given date to 00:00 hours.
     * @param date
     */
    protected static LocalDateTime floor(LocalDateTime date) {
        LocalDate day = LocalDate.from(date);
        LocalTime time = LocalTime.MIDNIGHT;
        // return TemporalAdjusters.midnight(date);
        return LocalDateTime.of(day, time);
    }

    /**
     * Rounds the given date to 23:59 hours.
     * @param date
     */
    protected static LocalDateTime ceiling(LocalDateTime date) {
        LocalDate day = LocalDate.from(date);
        LocalTime time = LocalTime.MAX;
        // return TemporalAdjusters.midnight(date);
        return LocalDateTime.of(day, time);
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
