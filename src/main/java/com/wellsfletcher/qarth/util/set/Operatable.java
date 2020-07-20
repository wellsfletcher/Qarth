package com.wellsfletcher.qarth.util.set;

/**
 * Represents ability to perform set operations.
 */
public interface Operatable<T> extends Union<T>, Intersection<T>, Difference<T>, Complement<T> {
    default T difference(T other) {
        Operatable<T> s = (Operatable<T>) other;
        return intersection(s.complement());
    }
}
