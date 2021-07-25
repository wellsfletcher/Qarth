package com.wellsfletcher.qarth.util.time.pattern;

import java.time.*;
import java.time.temporal.*;

/**
 * Represents collection of temporal expressions.
 */
public class DifferencePattern implements TemporalExpression {
    protected TemporalExpression included;
    protected TemporalExpression excluded;

    public DifferencePattern(TemporalExpression included, TemporalExpression excluded) {
        this.included = included;
        this.excluded = excluded;
    }

    public Temporal adjustInto(Temporal temporal) {
        LocalDateTime result;
        LocalDateTime date = LocalDateTime.from(temporal);
        if (included.includes(temporal) && !excluded.includes(temporal)) {
            result = date;
        } else {
            LocalDateTime in = date.with(included);
            LocalDateTime ex = date.with(excluded);
            // ope this is essentially impossible to do with just the given functions
            result = in; // too bad if the next occurrence is suppose to be excluded
            // result = null;
        }
        return result;
    }

    /*
    public boolean includes(Temporal temporal) {
        return included.includes(temporal) && !excluded.includes(temporal);
    }
    */
}
