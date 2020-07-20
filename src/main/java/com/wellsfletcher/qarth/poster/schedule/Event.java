package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.Temporal;

import java.util.List;

/**
 * Represents a calendar event.
 */
public class Event implements Schedulable {
    Runnable action;
    TemporalExpression pattern;

    public Event(Runnable action, TemporalExpression pattern) {
        this.action = action;
        this.pattern = pattern;
    }

    public void run() {
        action.run();
    }

    public Duration untilOccurrence(Temporal now) {
        return Duration.between(now, nextOccurrence(now));
    }

    public Temporal nextOccurrence(Temporal now) { // may be able to get away with these being type Temporal
        return now.with(pattern);
    }

    public boolean isOccurring(Temporal now) {
        return pattern.includes(now);
    }

    public String toString() {
        String result = "";

        result += "[";
        result += "isOccurring = " + isOccurring(Schedule.now());
        result += ", ";
        result += "occurrs at " + nextOccurrence(Schedule.now());
        result += ", ";
        result += "happens in " + untilOccurrence(Schedule.now());
        result += "]";

        return result;
    }
}
