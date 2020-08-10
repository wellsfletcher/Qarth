package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class ModPattern extends RepeatingDayPattern {
    LocalDateTime reference; // not necessary
    long modulo;
    long value;

    /*
    public ModPattern(LocalDateTime reference, ModCounter counter) {
        this(reference.toLocalDate(), counter.modulo(), counter.value());
        counter.increment();
    }
    */

    public ModPattern(LocalDateTime reference, long modulo, long value) {
        // this(reference.toLocalDate(), modulo, value);
        this(reference, modulo, value, Interval.reals());
    }

    public ModPattern(LocalDate reference, long modulo, long value) {
        this(reference.atStartOfDay(), modulo, value);
    }

    public ModPattern(LocalDateTime date, long modulo, long value, Interval span) {
        // super(Interval.fromDay(reference));
        // super(DayPattern.floor(date).plusDays(value - 0), modulo, span); // I think you need to round the date down first before call super
        super(date.plusDays(value), modulo, span);
        this.reference = interval.start;
        this.modulo = modulo;
        this.value = value;

        // visualize();
    }

    public Temporal adjustInto(Temporal temporal) {
          return super.adjustInto(temporal);
    }

    public void visualize() {
        Interval interval = new Interval(reference.minusDays(value + 1), Duration.ofDays(81));
        // Duration step = Duration.ofHours(1);
        TemporalExpression.visualize(this, interval);
    }

    public static long modulo(long a, long b) {
        try {
            return (a % b + b) % b;
        } catch (ArithmeticException e) {
            System.out.println("Failed to calculate " + a + " % " + b);
            // e.printStackTrace();
            // return 0;
            throw(e);
        }

        // return (a % b + b) % b;
    }
}
