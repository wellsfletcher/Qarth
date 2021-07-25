package com.wellsfletcher.qarth.util.time.advancedschedule;

import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import com.wellsfletcher.qarth.util.time.pattern.TomorrowPattern;
import com.wellsfletcher.qarth.util.time.schedule.Interval;
import com.wellsfletcher.qarth.util.time.schedule.Schedulable;
import com.wellsfletcher.qarth.util.time.schedule.Schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * Represents a calendar event.
 */
public class Event implements Planable {
    Runnable action;
    TemporalExpression pattern;
    Interval interval;

    protected boolean hasBeenRun = false;

    public Event(Runnable action, TemporalExpression pattern) {
        this.action = action;
        this.pattern = pattern;
        this.interval = Interval.fromDay(LocalDateTime.now().plusDays(1));
    }

    public Event(Runnable action) {
        this(action, Interval.fromDay(LocalDateTime.now().plusDays(1)));
    }

    public Event(Runnable action, Interval interval) {
        TemporalExpression pattern = new TomorrowPattern(Schedule.now().getDayOfWeek());
        this.action = action;
        this.pattern = pattern;
        this.interval = interval;
    }

    public void onStart() { // same as run method?
        // hasBeenRun = true;
    }

    public void onEnd(EventManager schedule) {
        // schedule next occurrence here
    }

    public Interval getInterval() {
        // if is occurring
        // return new Interval(nextOccurrence(), nextOccurrence());
        return interval;
    }

    public void run() {
        action.run();
        hasBeenRun = true;
        System.out.println("Running action event '" + action + "'.");
    }

    public boolean hasBeenRun() {
        return hasBeenRun;
    }

    /*
    public Duration untilOccurrence(Temporal now) {
        return Duration.between(now, nextOccurrence(now));
    }

    public Temporal nextOccurrence(Temporal now) { // may be able to get away with these being type Temporal
        return now.with(pattern);
    }

    public boolean isOccurring(Temporal now) {
        return pattern.includes(now);
    }
     */
    public Duration untilOccurrence(LocalDateTime now) {
        return Duration.between(now, interval.getStart());
    }

    public boolean isOccurring(LocalDateTime now) {
        return interval.contains(now);
    }

    /*
    public Temporal nextOccurrence(Temporal now) { // may be able to get away with these being type Temporal
        // should this return null if the interval has already passed?
        return null;
    }
     */

    public String toString() {
        String result = "";

        result += "[";
        result += "action = " + action;
        result += ", ";
        result += "isOccurring = " + isOccurring(Schedule.now());
        result += ", ";
        // result += "occurrs at " + nextOccurrence(Schedule.now());
        // result += ", ";
        result += "happens in " + untilOccurrence(Schedule.now());
        result += "]";

        return result;
    }
}
