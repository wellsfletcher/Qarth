package com.wellsfletcher.qarth.util.time.pattern;

import java.time.*;
import java.time.temporal.*;

import java.util.Collection;

/**
 * Represents collection of temporal expressions.
 */
public class UnionPattern extends CollectionPattern {
    public UnionPattern(Collection<TemporalExpression> expressions) {
        super(expressions);
    }

    public Temporal adjustInto(Temporal temporal) {
        // error if elements is empty
        LocalDateTime date = LocalDateTime.from(temporal);
        LocalDateTime soonest = null; // the adjusted element closest to temporal that is in the future
        LocalDateTime mostRecent = null; // closest one that is in the past
        for (TemporalExpression element : elements) {
            if (element.includes(temporal)) {
                return temporal;
            } else {
                // calculate soonest and mostrecent elements here
                // (even though this function isn't that important)
                LocalDateTime adjusted = LocalDateTime.from(temporal.with(element));
                if (adjusted.compareTo(date) > 0 && (soonest == null || adjusted.compareTo(soonest) < 0)) {
                    soonest = adjusted;
                } else if (mostRecent == null || adjusted.compareTo(mostRecent) > 0) {
                    mostRecent = adjusted;
                }
            }
        }

        // return soonest or mostRecent here
        if (soonest != null) {
            return soonest;
        } else {
            return mostRecent; // this should never be null
        }
    }
}
