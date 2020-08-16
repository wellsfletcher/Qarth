package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.Temporal;

import java.util.List;

/**
 * Represents system that can be manipulated via pause, resume, run, etc. commands.
 */
public interface Schedulable extends Runnable, Comparable<Schedulable> {
    public Temporal nextOccurrence(Temporal now); // no next occurrence == negative or null?
    public boolean isOccurring(Temporal now);
    public Duration untilOccurrence(Temporal now); // until next occurrence // this needs to be fixed; rn it's both not working and (most likely) is not useful

    default public Temporal nextOccurrence() {
        return nextOccurrence(Schedule.now());
    }

    default public boolean isOccurring() {
        return isOccurring(Schedule.now());
    }

    default public Duration untilOccurrence() {
        return untilOccurrence(Schedule.now());
    }

    default public boolean isFinished() {
        return isFinished(Schedule.now());
    }

    default public boolean isFinished(Temporal now) {
        return !isOccurring(now) && (untilOccurrence(now) == null || untilOccurrence(now).isNegative());
    }

    default public int compareTo(Schedulable other) {
        System.out.println("Comparing...");
        LocalDateTime now = Schedule.now();
        // if (other == null) return -1;
        if (other == null) {
            System.out.println("Other is null!");
        }
        Duration timeUntilThisEvent = this.untilOccurrence(now);
        Duration timeUntilOtherEvent = other.untilOccurrence(now);
        boolean isThisEventOccurring = isOccurring(now);
        boolean isOtherEventOccurring = other.isOccurring(now);

        if (timeUntilThisEvent == null && timeUntilOtherEvent == null) return 0;
        if (timeUntilThisEvent == null) return -1;
        if (timeUntilOtherEvent == null) return 1;

        return timeUntilThisEvent.compareTo(timeUntilOtherEvent);
        // return this.untilOccurrence(now).compareTo(other.untilOccurrence(now));
    }

    /*
    default public String toString() {
        String result = "";

        result += "isOccurring = " + isOccurring(Schedule.now());
        result += ", ";
        // result += "happens in = " isOccurring;
        result += "occurrs at " + nextOccurrence(Schedule.now());

        return result;
    }
    */
}
