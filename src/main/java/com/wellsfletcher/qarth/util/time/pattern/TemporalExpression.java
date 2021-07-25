package com.wellsfletcher.qarth.util.time.pattern;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

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

    public static void visualize(TemporalExpression pattern, Interval interval) {
        Duration step = Duration.ofHours(1);
        visualize(pattern, interval, step);
    }

    public static void visualize(TemporalExpression pattern, Interval interval, Duration step) {
        // step in seconds
        LocalDateTime start = interval.getStart();
        LocalDateTime end = interval.getStart();
        long durationInSeconds = interval.getDuration().getSeconds();
        long stepInSeconds = step.getSeconds();
        for (long k = 0; k < durationInSeconds; k += stepInSeconds) {
            LocalDateTime current = start.plusSeconds(k);
            String dateString = formatter.format(current);

            String includesIndicator = "-----";
            if (pattern.includes(current)) {
                includesIndicator = "ooooo";
            }

            System.out.println(includesIndicator + " => " + dateString);
        }

    }

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
