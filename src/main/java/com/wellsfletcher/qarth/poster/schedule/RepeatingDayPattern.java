package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

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
