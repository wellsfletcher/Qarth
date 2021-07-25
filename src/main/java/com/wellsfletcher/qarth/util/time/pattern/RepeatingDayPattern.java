package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.*;
import java.time.temporal.*;

/**
 * Represents TemporalExpression.
 */
public class RepeatingDayPattern extends FixedRepeatingIntervalPattern {
    public RepeatingDayPattern(LocalDateTime date, Long days, Interval span) {
        // super(DayPattern.day(date), span, Duration.ofDays(days - 1));  // the minus 1 is very notable
        super(DayPattern.day(date), span, Duration.ofDays(days));
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
