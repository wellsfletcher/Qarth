package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class TomorrowPattern extends TemporalAdjusterExpression {
    public TomorrowPattern(DayOfWeek day) {
        super(TemporalAdjusters.nextOrSame(day));
    }
}
