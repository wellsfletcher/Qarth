package com.wellsfletcher.qarth.util.time.pattern;

import java.time.temporal.*;

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
