package com.wellsfletcher.qarth.poster.schedule;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.time.*;
import java.time.temporal.*;

import java.util.Set;
import java.util.Collection;

/**
 * Represents collection of temporal expressions.
 */
public abstract class CollectionPattern implements TemporalExpression {
    Collection<TemporalExpression> elements;

    public CollectionPattern(Collection<TemporalExpression> expressions) {
        this.elements = expressions;
    }

    abstract public Temporal adjustInto(Temporal temporal);
}
