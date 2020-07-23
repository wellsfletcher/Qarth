package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Represents a schedule.
 */
public class Schedule implements Schedulable {
    PriorityQueue<Schedulable> events;

    public Schedule() {
        // System.out.println("Initialized!");
        this.events = new PriorityQueue<>();
    }

    public void run() {
        if (!events.isEmpty()) { // issues
            System.out.println("Beginning schedule execution.");
            Schedulable event = events.remove();
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            Duration delay = event.untilOccurrence(now());
            long millis = delay.toMillis();
            // service.schedule(event, millis, TimeUnit.MILLISECONDS);
            System.out.println("About to start thread.");
            service.schedule(() -> {
                System.out.println("Woah, I am in a thread.");
                long millis2 = event.untilOccurrence(Schedule.now()).toMillis();
                sleep(millis2);
                event.run();
                // sleep here?
                // long millis = event.untilOccurrence(Schedule.now());
                // Thread.sleep(2000);
                while (event.isOccurring(now())) {
                    sleep(10);
                }
                // could also tell the event to stop here... in the case that the thread may have started another thread
                // ... or I could move this stuff out of here so then the action can take as long as it wants doing its thing
                add(event);
                run();
                System.out.println("Ok, this thread is done.");
            }, millis, TimeUnit.MILLISECONDS);
            service.shutdown();
        } else {
            System.out.println("Schedule is finished.");
        }
    }

    public Duration untilOccurrence(Temporal now) {
        Duration result = null;

        if (!events.isEmpty()) {
            result = events.remove().untilOccurrence(now);
        }

        return result;
    }

    public Temporal nextOccurrence(Temporal now) {
        Temporal result = null;

        if (!events.isEmpty()) {
            result = events.remove().nextOccurrence(now);
        }

        return result;
    }

    public boolean isOccurring(Temporal now) {
        if (events.isEmpty()) return false;

        Schedulable event = events.peek();
        return event.isOccurring(now);
    }

    public void forTomorrow(Runnable action) {
        // TemporalExpression pattern = new DayPattern(now().plusDays(1));
        TemporalExpression pattern = new TomorrowPattern(now().getDayOfWeek());
        // int priority = events.size();
        // Event event = new Event(action, pattern, priority);
        Event event = new Event(action, pattern); // causing issues
        add(event);
    }

    /*
    public void forDuration(Runnable action, LocalDateTime start, Duration duration) {
        TemporalExpression pattern = new IntervalPattern(now(), duration);
        Event event = new Event(action, pattern);
        add(event);
    }
    */
    public void afterDuration(Runnable action, Duration duration) {
        // Duration length = Duration.ofMinutes(1);
        Duration length = Duration.ofSeconds(10);
        TemporalExpression pattern = new DurationPattern(duration, length);
        Event event = new Event(action, pattern);
        add(event);
    }

    public void add(Runnable action, TemporalExpression pattern) {
        Event event = new Event(action, pattern);
        add(event);
    }

    public void add(Schedulable event) {
        System.out.println("to add = " + event);
        if (event.isFinished(now())) return; // ideally shouldn't need to exist
        // if (event == null) return; // ideally shouldn't need to exist
        events.add(event);
        System.out.println("events = " + events);
        System.out.println("Event added!");
    }

    /**
     * Clears all events contained in this schedule
     */
    public void clear() {
        System.out.println("Clearing...");
        events.clear();
        System.out.println("Cleared.");
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis); // this needs to be different
        } catch (InterruptedException error) {
            System.out.println("Interrupted. No longer sleeping for " + millis + " milliseconds.");
        }
    }

    // public void run();
    // public void stop();

    public String toString() {
        String result = "";

        result += events;

        return result;
    }
}
