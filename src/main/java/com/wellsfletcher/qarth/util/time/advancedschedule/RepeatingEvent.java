package com.wellsfletcher.qarth.util.time.advancedschedule;

import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import com.wellsfletcher.qarth.util.time.pattern.TomorrowPattern;
import com.wellsfletcher.qarth.util.time.schedule.Interval;
import com.wellsfletcher.qarth.util.time.schedule.Schedule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RepeatingEvent extends Event {
    private Duration repeatDuration;

    public RepeatingEvent(Runnable action, Interval interval, Duration repeatDuration) {
        super(action, interval);
        this.repeatDuration = repeatDuration;
    }

    public void onEnd(EventManager schedule) {
        LocalDateTime now = schedule.now();
        hasBeenRun = false;
        // schedule next occurrence here
        // must make sure that the next occurrence is scheduled for after the current time
        // could this also be done with mod?
        // y = ((int) x + 1)
        // nextStartTime >= now + repeatDuration * ((int) x + 1)
        // nextStartTime = now + repeatDuration * x
        // (nextStartTime - now) / repeatDuration = x
        // add repeatDuration to interval
        // may change start time to end time? otherwise it might miss it...
        /*
        long st = interval.getStartEpochSeconds();
        long et = interval.getEndEpochSeconds();
        long nt = now.toEpochSecond(ZoneOffset.UTC);
        long duration = repeatDuration.getSeconds();

        // please fix this later
        long nextSt = st;
        while (nextSt < nt) {
            nextSt += duration;
        }
        // long nextSt = st + ((nt - st) % duration);
        // long delta = ((nt - st) % duration);
        LocalDateTime nextStartTime = LocalDateTime.ofEpochSecond(nextSt, 0, ZoneOffset.UTC); // the zone offsets could be wrong
        LocalDateTime nextEndTime = LocalDateTime.ofEpochSecond(nextSt + interval.getDuration().getSeconds(), 0, ZoneOffset.UTC);

        // Interval nextInterval = interval.plus(repeatDuration);
        Interval nextInterval = new Interval(nextStartTime, nextEndTime);
        interval = nextInterval;
        schedule.add(this);
         */

        while (interval.getStart().compareTo(now) < 0) {
            interval = interval.plus(repeatDuration);
        }
        schedule.add(this);

        /*
        Interval nextInterval = interval.plus(repeatDuration);
        interval = nextInterval;
        schedule.add(this);
         */
    }
}
