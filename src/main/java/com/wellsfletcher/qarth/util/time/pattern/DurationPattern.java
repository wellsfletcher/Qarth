package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;
import com.wellsfletcher.qarth.util.time.schedule.Schedule;

import java.time.*;

/**
 * Represents TemporalExpression.
 */
public class DurationPattern extends IntervalPattern {
    public DurationPattern(Duration delay, Duration duration) {
        super(new Interval(Schedule.now().plus(delay), duration));
    }
}
