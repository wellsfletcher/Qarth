package com.wellsfletcher.qarth.util.time.pattern;

import java.time.temporal.*;

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
