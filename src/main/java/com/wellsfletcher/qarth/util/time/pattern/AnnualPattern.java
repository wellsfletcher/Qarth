package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.*;
import java.time.temporal.*;

/**
 * Represents TemporalExpression.
 */
public class AnnualPattern extends RepeatingDayPattern {
    public AnnualPattern(LocalDateTime date) {
        super(date, 365L, Interval.reals());
    }

    public AnnualPattern(LocalDateTime date, Interval span) {
        super(date, 365L, span);
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
