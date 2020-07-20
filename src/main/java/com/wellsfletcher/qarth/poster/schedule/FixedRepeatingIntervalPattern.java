package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class FixedRepeatingIntervalPattern extends RepeatingIntervalPattern {
    protected Duration delay;

    public FixedRepeatingIntervalPattern(Interval interval, Interval span, Duration fixedDelay) {
        super(interval, span);
        this.delay = delay;
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
