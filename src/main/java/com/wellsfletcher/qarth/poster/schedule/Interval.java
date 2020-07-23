package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;
import com.wellsfletcher.qarth.util.set.Operatable;

import java.time.*;
import java.time.temporal.*;
import java.util.Objects;

import java.util.List;

/**
 * Represents a local date time interval, with the start inclusive and the end exclusive.
 * Note that Inteval class in ThreeTen package was used as a reference, and can be found at https://github.com/ThreeTen/threeten-extra/blob/e834074dddbb95719092afbfb09a0b4307a9eaf1/src/main/java/org/threeten/extra/Interval.java.
 */
public class Interval implements Comparable<Interval>, Operatable<Interval> {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected Duration duration;

    public Interval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.duration = Duration.between(start, end);
    }

    public Interval(LocalDateTime start, Duration duration) {
        this.start = start;
        this.end = start.plus(duration);
        this.duration = duration;
    }

    public static Interval of(LocalDateTime start, LocalDateTime end) {
        return new Interval(start, end);
    }

    public static Interval of(LocalDateTime start, Duration duration) {
        return new Interval(start, duration);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Duration getDuration() {
        return duration;
    }

    public int compareTo(Interval other) {
        return 0;
    }

    /**
     * Checks if this interval contains the specified instant.
     *
     * @param instant  the instant, not null
     * @return true if this interval contains the instant
     */
    public boolean contains(LocalDateTime instant) {
        Objects.requireNonNull(instant, "instant");
        return start.compareTo(instant) <= 0 && (instant.compareTo(end) < 0 || isUnboundedEnd());
    }

    /**
     * Checks if this interval encloses the specified interval.
     *
     * @param other  the other interval, not null
     * @return true if this interval contains the other interval
     */
    public boolean encloses(Interval other) {
        Objects.requireNonNull(other, "other");
        return start.compareTo(other.start) <= 0 && other.end.compareTo(end) <= 0;
    }

    /**
     * Checks if this interval abuts the specified interval.
     *
     * @param other  the other interval, not null
     * @return true if this interval abuts the other interval
     */
    public boolean abuts(Interval other) {
        Objects.requireNonNull(other, "other");
        return end.equals(other.start) ^ start.equals(other.end);
    }

    /**
     * Checks if this interval is connected to the specified interval.
     *
     * @param other  the other interval, not null
     * @return true if this interval is connected to the other interval
     */
    public boolean isConnected(Interval other) {
        Objects.requireNonNull(other, "other");
        return this.equals(other) || (start.compareTo(other.end) <= 0 && other.start.compareTo(end) <= 0);
    }

    /**
     * Checks if this interval overlaps the specified interval.
     *
     * @param other  the time interval to compare to, null means a zero length interval now
     * @return true if the time intervals overlap
     */
    public boolean overlaps(Interval other) {
        Objects.requireNonNull(other, "other");
        return other.equals(this) || (start.compareTo(other.end) < 0 && other.start.compareTo(end) < 0);
    }

    /**
     * Calculates the interval that is the intersection of this interval and the specified interval.
     *
     * @param other  the other interval to check for, not null
     * @return the interval that is the intersection of the two intervals
     */
    public Interval intersection(Interval other) {
        Objects.requireNonNull(other, "other");
        int cmpStart = start.compareTo(other.start);
        int cmpEnd = end.compareTo(other.end);
        if (cmpStart >= 0 && cmpEnd <= 0) {
            return this;
        } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return other;
        } else {
            LocalDateTime newStart = (cmpStart >= 0 ? start : other.start);
            LocalDateTime newEnd = (cmpEnd <= 0 ? end : other.end);
            return Interval.of(newStart, newEnd);
        }
    }

    /**
     * Calculates the interval that is the union of this interval and the specified interval.
     *
     * @param other  the other interval to check for, not null
     * @return the interval that is the union of the two intervals
     */
    public Interval union(Interval other) {
        Objects.requireNonNull(other, "other");
        int cmpStart = start.compareTo(other.start);
        int cmpEnd = end.compareTo(other.end);
        if (cmpStart >= 0 && cmpEnd <= 0) {
            return other;
        } else if (cmpStart <= 0 && cmpEnd >= 0) {
            return this;
        } else {
            LocalDateTime newStart = (cmpStart >= 0 ? other.start : start);
            LocalDateTime newEnd = (cmpEnd <= 0 ? other.end : end);
            return Interval.of(newStart, newEnd);
        }
    }

    /**
     * Calculates the "positive" portion of the complement of this interval.
     *
     * @return the interval that is the complement of this interval
     */
    public Interval complement() {
        return Interval.of(end, LocalDateTime.MAX);
    }

    /*
     * Calculates the interval that is the difference of this interval and the specified interval.
     *
     * @param other the other interval to check for, not null
     * @return the interval that is the difference of the two intervals
     *
    public Interval difference(Interval other) { // this is automatically defined in the operatable interface
        Objects.requireNonNull(other, "other");

    }
     */

    /**
     * Checks if this interval is after the specified instant.
     *
     * @param instant  the other instant to compare to, not null
     * @return true if the start of this interval is after the specified instant
     */
    public boolean isAfter(LocalDateTime instant) {
        return start.compareTo(instant) > 0;
    }

    /**
     * Checks if this interval is before the specified instant.
     *
     * @param instant  the other instant to compare to, not null
     * @return true if the start of this interval is before the specified instant
     */
    public boolean isBefore(LocalDateTime instant) {
        return end.compareTo(instant) <= 0 && start.compareTo(instant) < 0;
    }

    /**
     * Checks if the range is empty.
     * @return true if the range is empty
     */
    public boolean isEmpty() {
        return start.equals(end);
    }

    /**
     * Checks if the start of the interval is unbounded.
     * @return true if start is unbounded
     */
    public boolean isUnboundedStart() {
        return start.equals(LocalDateTime.MIN);
    }

    /**
     * Checks if the end of the interval is unbounded.
]     * @return true if end is unbounded
     */
    public boolean isUnboundedEnd() {
        return end.equals(LocalDateTime.MAX);
    }

    public static LocalDateTime MIN = LocalDateTime.MIN;
    public static LocalDateTime MAX = LocalDateTime.MAX;

    /**
     * Returns a copy of this range with the specified start instant.
     *
     * @param start the start instant for the new interval, not null
     * @return an interval with the end from this interval and the specified start
     */
    public Interval withStart(LocalDateTime start) {
        return Interval.of(start, end);
    }

    /**
     * Returns a copy of this range with the specified end instant.
     *
     * @param end the end instant for the new interval, not null
     * @return an interval with the start from this interval and the specified end
     */
    public Interval withEnd(LocalDateTime end) {
        return Interval.of(start, end);
    }

    public static Interval fromDay(LocalDateTime date) {
        return fromDay(date.toLocalDate());
    }

    public static Interval fromDay(LocalDate date) {
        return new Interval(date.atStartOfDay(), date.atStartOfDay().plusDays(1));
    }

    /**
     * Checks if this interval is equal to another interval.
     *
     * Compares this {@code Interval} with another ensuring that the two instants are the same.
     * Only objects of type {@code Interval} are compared, other types return false.
     *
     * @param obj the object to check, null returns false
     * @return true if this is equal to the other interval
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Interval) {
            Interval other = (Interval) obj;
            return start.equals(other.start) && end.equals(other.end);
        }
        return false;
    }
}
