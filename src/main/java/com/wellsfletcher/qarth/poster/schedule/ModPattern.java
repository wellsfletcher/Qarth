package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.List;

/**
 * Represents TemporalExpression.
 */
public class ModPattern extends RepeatingIntervalPattern {
    LocalDateTime reference;
    Integer modulo;
    Integer value;

    /*
    public ModPattern(LocalDateTime reference, ModCounter counter) {
        this(reference.toLocalDate(), counter.modulo(), counter.value());
        counter.increment();
    }
    */

    public ModPattern(LocalDateTime reference, Integer modulo, Integer value) {
        this(reference.toLocalDate(), modulo, value);
    }

    public ModPattern(LocalDate reference, Integer modulo, Integer value) {
        super(Interval.fromDay(reference));
        this.reference = reference.atStartOfDay();
        this.modulo = modulo;
        this.value = value;

    }

    public Temporal adjustInto(Temporal temporal) {
          LocalDateTime date = LocalDateTime.from(temporal);
          // IntervalPattern current = Interval.fromDay(date);
          Period duration = Period.between(reference.toLocalDate(), date.toLocalDate());
          long days = duration.getDays();
          long mod = days % modulo;
          if (mod == value) {
              return date;
          } else {
              // this doesn't take into account start and end dates as of now
              return date.plusDays(mod);
          }
    }
}
