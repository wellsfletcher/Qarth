package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public abstract class TemporalAdjusterExpression implements TemporalExpression {
    protected TemporalAdjuster adjuster;

    public TemporalAdjusterExpression(TemporalAdjuster adjuster) {
        this.adjuster = adjuster;
    }

    public Temporal adjustInto(Temporal temporal) {
        return adjuster.adjustInto(temporal);
    }
}
