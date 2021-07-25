package com.wellsfletcher.qarth.util.time.advancedschedule;

import com.wellsfletcher.qarth.util.time.schedule.Interval;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.List;

public class EventManager implements Planable {

    private final IntervalMap<Event> map = new IntervalMap<Event>();

    /**
     * Set all values in [x, y) to be value
     * Adds an event during the given time range
     */
    private void set(long x, long y, Event value) {
        map.set((int) x, (int) y, value);
    }

    /**
     * @return value at x
     * Gets an event at the current instance of time
     */
    public Event get(int x) {
        return map.get(x);
    }

    /**
     * Adds an event to the manager
     * Schedule method
     */
    public void add(Event event) {
        Interval interval = event.getInterval(); // getNextInterval(now())
        long s = interval.getStartEpochSeconds();
        long e = interval.getEndEpochSeconds();
        set(s, e, event);
        // return map.get(x);
    }

    /**
     * Schedule method
     */
    public Collection<Event> getEvents() {
        return map.values();
    }

    /**
     * Schedule method
     */
    public void add(Collection<Event> events) {
        for (Event event : events) {
            add(event);
        }
    }

    /**
     * Schedule method
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void run() {
        // right now, every poster has an internal schedule that gets ran when the poster is ran
        // a poster collection copies all the contents of its child schedules to its own schedule

        // events that repeat may get forgotten about
        // // additionally, every repetition of an event doesn't make it get run again
        // the repeating events are getting covered by the other ones and don't get a chance to add themselves back
        // could try to keep adding the event until it doesn't overlap / get covered by another event

        Event currentEvent = null;
        boolean eventChanged = false;
        while (!map.isEmpty()) {
            Event event = map.firstEntry();
            // if the next event is not occurring right now and occurred in the past,
            // then we need to remove the event
            if (!event.isOccurring(now()) && event.untilOccurrence(now()).compareTo(Duration.ZERO) < 0) { // can the duration be negative?
                map.removeFirstEntry();
                // currentEvent.onEnd(this); // also give it the current time?
                event.onEnd(this); // also give it the current time?
                //- event = map.firstEntry();
                // the event would have now just changed
                print();
                eventChanged = true;
            } else if (eventChanged || (!event.hasBeenRun() && event.isOccurring(now()))) { // } else if (eventChanged || (currentEvent != event && event.isOccurring(now()))) {
                // the event has recently just changed
                // and the event is currently occurring
                currentEvent = event; // map.firstEntry();
                // event.onStart();
                event.run();
                eventChanged = false;
            }
            /*
            if (currentEvent != event) {
                // if event just changed
                currentEvent.onEnd(this);
                currentEvent = event; // map.firstEntry();
                // event.onStart();
                event.run();
            }
             */
        }
    }

    public LocalDateTime now() {
        // return (int) LocalDateTime.now().toEpochSecond();
        // return Instant.now().getEpochSecond();
        return LocalDateTime.now();
    }

    public Duration untilOccurrence(LocalDateTime now) {
        Duration result = null;

        if (!isEmpty()) {
            result = map.firstEntry().untilOccurrence(now);
        }

        return result;
    }

    public boolean isOccurring(LocalDateTime now) {
        if (isEmpty()) return false;

        Planable event = map.firstEntry();
        return event.isOccurring(now);
    }

    public void print() {
        map.print();
    }

    // public T getCurrentEvent() {}
    // public T getNextEvent() {}
    /**
     * Needs to be able to:
     * -be run
     * -let an event know that it has been finished
     * -let an event know that it has started
     * -events should be able to get the next N events
     * -can either sleep until the next event happens
     *      -would need to know the duration until the next event
     *      -could be constantly be removing the lowest range, so then we can the next range that will happen
     * -or poll the event occurring at the current time
     * -or asynchronously schedule a bunch of events to happen after a period of time
     */
}