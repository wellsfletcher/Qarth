package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class FixedRepeatingIntervalPattern extends RepeatingIntervalPattern {
    protected Duration delay; // is cycle duration as of now

    public FixedRepeatingIntervalPattern(Interval interval, Interval span, Duration delay) {
        super(interval, span);
        this.delay = delay;
    }



    public Temporal adjustInto(Temporal temporal) {
        LocalDateTime date = LocalDateTime.from(temporal);
        ChronoUnit unit = ChronoUnit.SECONDS;

        // mod (the difference between the start date and the given date) by the (the duration of the interval + the duration of the delay)
        // Duration timeSinceStart = Duration.between(date, interval.start);
        Duration timeSinceStart = Duration.between(interval.start, date);
        Duration cycleDuration = interval.getDuration().plus(delay); // also the wavePeriod
        long timeSinceStartAsLong = timeSinceStart.get(unit);
        long cycleDurationAsLong = cycleDuration.get(unit); // could very much be implemented this way
        long intervalDurationAsLong = interval.duration.get(unit);
        long delayDurationAsLong = delay.get(unit);
        //- long timeSinceLastOccurrenceAsLong = ModPattern.modulo(timeSinceStartAsLong, cycleDurationAsLong);
        long timeSinceLastOccurrenceAsLong = ModPattern.modulo(timeSinceStartAsLong, delayDurationAsLong);
        // if this duration is less than the duration of the interval, then
        // System.out.println("total elapse time = " + timeSinceStartAsLong + ", thus " +  timeSinceLastOccurrenceAsLong + " < " + intervalDurationAsLong);
        if (timeSinceLastOccurrenceAsLong <= intervalDurationAsLong) {
            // return the given date (it's included)
            return temporal;
        }
        // otherwise,
        else {
            // use (the time since the start of the interval divided by the cycle duration) + 1 to get the index of the next occurrence (without the + 1 would be the index of the previous occurrence)
            long occurrenceID = (timeSinceStartAsLong / delayDurationAsLong);
            if (timeSinceStartAsLong >= 0) {
                occurrenceID += 1;
            }
            // return the occurrence index * the cycle duration (to get the time of the next occurrence) + the start date
            long timeSinceStartFromLastOccurrenceAsLong = occurrenceID * delayDurationAsLong;
            // LocalDateTime timeOfNextOccurrence = date.plus(Duration.ofMillis(timeSinceStartFromLastOccurrenceAsLong));
            // LocalDateTime timeOfNextOccurrence = date.plus(Duration.of(timeSinceStartFromLastOccurrenceAsLong, unit));
            LocalDateTime timeOfNextOccurrence = interval.start.plus(Duration.of(timeSinceStartFromLastOccurrenceAsLong, unit));
            // System.out.println("time of next occurrence = " + timeOfNextOccurrence);
            return timeOfNextOccurrence;
        }
    }
}
