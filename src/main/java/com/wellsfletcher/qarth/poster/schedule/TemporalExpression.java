package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public interface TemporalExpression extends TemporalAdjuster {
    public Temporal adjustInto(Temporal temporal);

    // public Duration left(Temporal temporal); // returns the time left before the current event is over

    default public boolean includes(Temporal date) {
        Temporal adjusted = adjustInto(date);
        // Temporal adjusted = date.with(this);
        return date.equals(adjusted);
    }
}
