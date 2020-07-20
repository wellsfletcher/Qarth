package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class RepeatingIntervalPattern extends IntervalPattern {
    protected Duration repeatDuration;

    public RepeatingIntervalPattern(LocalDateTime start, Duration duration, Duration repeatDuration) {
        super(start, duration);
        this.repeatDuration = repeatDuration;
    }

    public Temporal adjustInto(Temporal temporal) {
          // LocalDateTime date = LocalDateTime.from(input);
          return super.adjustInto(temporal);
    }
}
