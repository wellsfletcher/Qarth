package com.wellsfletcher.qarth.util.time.advancedschedule;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Efficient data structure to map values to intervals
 * e.g. set(5, 60000, "hello") would set all keys in [5, 60000) to be "hello"
 *
 * All operations are O(log n) (in practice much faster since n is usually number of segments)
 * This data structure doesn't allow you to easily remove whole elements. It may not work at all...
 *
 * @param <T> The type of data to store
 */
public class IntervalMap<T> {

    private TreeMap<Integer, Segment> segments = new TreeMap<Integer, Segment>();

    private class Segment {
        final int start, end;
        final T value;

        Segment(int start, int end, T value) {
            this.start = start;
            this.end = end;
            this.value = value;
            // todo: meld here
            segments.put(start, this);
        }

        void destroy() {
            segments.remove(start);
        }

        boolean contains(int x) {
            return start <= x && x < end;
        }

        public String toString() {
            return "[" + this.start + "," + this.end + "," + this.value + "]";
        }
    }

    private Segment ceiling(int x) {
        return extract(segments.ceilingEntry(x));
    }

    private Segment floor(int x) {
        return extract(segments.floorEntry(x));
    }

    private Segment extract(Entry<Integer, Segment> e) {
        return e != null ? e.getValue() : null;
    }

    private Segment find(int x) {
        final Segment prev = floor(x);
        return prev != null && prev.contains(x) ? prev : ceiling(x);
    }

    /**
     * Set all values in [x, y) to be value
     */
    public void set(int x, int y, T value) {
        System.out.println(x + " < " + y);
        // print();
        assert x < y;
        final Segment s = find(x);

        if(s == null) {
            new Segment(x, y, value);
        } else if(x < s.start) {
            if(y <= s.start) {
                // s.addChild(new Segment(x, y, value))
                new Segment(x, y, value);
            } else if(y < s.end) {
                s.destroy();
                new Segment(x, y, value);
                new Segment(y, s.end, s.value);
            } else {
                s.destroy();
                new Segment(x, s.end, value);
                set(s.end, y, value);
            }
        } else if(x < s.end) {
            s.destroy();
            new Segment(s.start, x, s.value);
            if(y < s.end) {
                new Segment(x, y, value);
                new Segment(y, s.end, s.value);
            } else {
                new Segment(x, s.end, value);
                set(s.end, y, value);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * @return value at x
     */
    public T get(int x) {
        final Segment s = floor(x);
        return s != null && s.contains(x) ? s.value : null;
    }

    public boolean isEmpty() {
        return segments.isEmpty();
    }

    private Integer firstEntryKey() {
        if (isEmpty()) return null;
        return segments.firstEntry().getKey();
    }

    public T firstEntry() {
        return segments.firstEntry().getValue().value;
    }

    public void removeFirstEntry() {
        // as of now this may also remove other entries with the same value that weren't intended to be removed
        // but it should work fine for my purposes
        // Integer key = firstEntryKey();
        Segment s = segments.remove(firstEntryKey());
        T value = s.value;
        removeValue(value);
    }

    public Collection<T> values() {
        return segments.values().stream().map(segment -> segment.value).collect(Collectors.toList());
    }

    /**
     * Removes all entries associated with the given value.
     * Currently runs in O(n) time
     * @param value
     */
    public void removeValue(T value) {
        // could also potentially return the set of all values that were removed
        /*
        for (Entry<Integer, Segment> entry : segments.entrySet()) {
            Integer key = entry.getKey();
            Segment entryValue = entry.getValue();
            if (entryValue.value == value) {
                segments.remove(key);
            }
        }
         */
        TreeMap<Integer, Segment> tempMap = new TreeMap<Integer, Segment>();
        tempMap.putAll(segments);
        Set<Entry<Integer, Segment>> entries = segments.entrySet();
        for (Entry<Integer, Segment> entry : entries) {
            Integer key = entry.getKey();
            Segment entryValue = entry.getValue();
            if (entryValue.value == value) {
                // segments.remove(key);
                tempMap.remove(key);
            }
        }
        // may wanna keep the segments reference the same
        segments = tempMap;
    }

    public void print() {
        //  Collection<T> values = values();
        // if (isEmpty()) return;
        Integer lowest = firstEntryKey();
        System.out.println("start = " + lowest);
        for (Entry<Integer, Segment> entry : segments.entrySet()) {
            Integer key = entry.getKey();
            Segment value = entry.getValue();
            String valueStr = "[" + (value.start - lowest) + "," + (value.end - lowest) + "," + value.value + "]";  // value.toString();

            System.out.printf("%s : %s\n", key, valueStr);
        }
    }
}