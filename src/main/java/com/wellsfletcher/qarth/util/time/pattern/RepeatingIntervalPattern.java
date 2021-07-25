package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.temporal.*;

/**
 * Represents TemporalExpression.
 */
public abstract class RepeatingIntervalPattern extends IntervalPattern {
    protected Interval span;

    public RepeatingIntervalPattern(Interval interval) {
        this(interval, new Interval(Interval.MIN, Interval.MAX));
    }

    /**
     * @param span the span in which events should repeat
     */
    public RepeatingIntervalPattern(Interval interval, Interval span) {
        super(interval);
        this.span = span;
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
