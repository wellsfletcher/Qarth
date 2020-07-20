package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class DurationPattern extends IntervalPattern {
    public DurationPattern(Duration delay, Duration duration) {
        super(Schedule.timeNow().plus(delay), duration);
    }
}
