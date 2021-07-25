package com.wellsfletcher.qarth.util.time.pattern;

import java.time.*;
import java.time.temporal.*;

/**
 * Represents TemporalExpression.
 */
public class TomorrowPattern extends TemporalAdjusterExpression {
    public TomorrowPattern(DayOfWeek day) {
        super(TemporalAdjusters.nextOrSame(day));
    }
}
