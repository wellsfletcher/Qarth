package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public abstract class RepeatingIntervalPattern extends IntervalPattern {
    protected Interval span;

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
