package com.wellsfletcher.qarth.util.time.schedule;

import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;

import java.time.*;
import java.time.temporal.Temporal;

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
        System.out.println("Running action event '" + action + "'.");
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
        result += "action = " + action;
        result += ", ";
        result += "isOccurring = " + isOccurring(Schedule.now());
        result += ", ";
        result += "occurrs at " + nextOccurrence(Schedule.now());
        result += ", ";
        result += "happens in " + untilOccurrence(Schedule.now());
        result += "]";

        return result;
    }
}
