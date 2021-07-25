package com.wellsfletcher.qarth.util.time.pattern;

import java.time.*;
import java.time.temporal.*;

import java.util.Collection;

/**
 * Represents collection of temporal expressions.
 */
public class IntersectionPattern extends CollectionPattern {
    public IntersectionPattern(Collection<TemporalExpression> expressions) {
        super(expressions);
    }

    public Temporal adjustInto(Temporal temporal) {
        // error if elements is empty
        LocalDateTime date = LocalDateTime.from(temporal);
        LocalDateTime soonest = null; // the adjusted element closest to temporal that is in the future
        LocalDateTime mostRecent = null; // closest one that is in the past
        for (TemporalExpression element : elements) {
            if (!element.includes(temporal)) {
                // calculate soonest and mostrecent elements here
                // (even though this function isn't that important)
                LocalDateTime adjusted = LocalDateTime.from(temporal.with(element));
                if (adjusted.compareTo(date) > 0 && (soonest == null || adjusted.compareTo(soonest) < 0)) {
                    soonest = adjusted;
                } else if (mostRecent == null || adjusted.compareTo(mostRecent) > 0) {
                    mostRecent = adjusted;
                }

                if (soonest != null) {
                    return soonest;
                } else {
                    return mostRecent; // this should never be null
                }
            } else {
                LocalDateTime adjusted = LocalDateTime.from(temporal.with(element));
                if (adjusted.compareTo(date) > 0 && (soonest == null || adjusted.compareTo(soonest) < 0)) {
                    soonest = adjusted;
                } else if (mostRecent == null || adjusted.compareTo(mostRecent) > 0) {
                    mostRecent = adjusted;
                }
            }
        }

        // return soonest or mostRecent here
        return temporal;
    }
}
