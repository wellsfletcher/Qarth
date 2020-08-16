package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.poster.schedule.ModPattern;
import com.wellsfletcher.qarth.poster.schedule.Schedule;
import com.wellsfletcher.qarth.poster.schedule.*;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import java.io.*;
import java.util.Scanner;
import java.time.*;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Manages daily dynamic poster
 */
public abstract class PosterCollection extends EmptyPoster {
    private static final String SOURCES_FOLDER_NAME = "resources";
    protected List<Poster> posters = new LinkedList<>();

    /*
    protected void setup() {
        posters = new LinkedList<>();
        posters = createSourcePosters();
    }
    */

    public PosterCollection(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 5, 4);
    }

    public PosterCollection(String hostURL, String contentDirectory, String name, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);

        FileSystem.makeDirectory(getResourcesPath());
        posters = createSourcePosters(); // may move this so subclasses can more easily use instance variables
        schedule();
    }

    protected String getResourcesPath() {
        return getContentPath() + SOURCES_FOLDER_NAME + DELIM;
    }

    protected String getResourcesURL() {
        return getContentURL() + SOURCES_FOLDER_NAME + DELIM;
    }

    protected void image(List<String> links, String path) {
        super.image(links, path);
        // create the rest of the posters in the sources folder here
        for (Poster poster : posters) {
            // move the poster to the resources folder here
            poster.create();
        }
    }

    /*
    public LocalDateTime update(LocalDateTime date) {
        LocalDateTime result = Dynamic.tomorrow();
        Set<Poster> posters = getSourcePosters();
        Poster poster = posters.get(poster.size() % day);
        // poster.copy(here());
        // poster.create();
        activate(Poster poster);
        return result;
    }
    */

    /*
    private static class WaitToActivatePoster extends TimerTask {
        private Poster poster;
        public WaitToActivatePoster(Poster poster) {
            this.poster = poster;
        }

        public void run() {
            activate(poster);
        }
    }
    */

    public void run() {
        // schedule.clear(); // temporary?

        for (Poster poster : posters) {
            poster.move(this);
            // System.out.println("Adding " + poster.name + " poster events.");
            // schedule.add(poster.schedule); // operation should be equivalent to adding an array to another array
        }

        // super.run();
        schedule.run(); // delete later

        // may need to move the posters back afterwards
    }

    protected void schedule() {
        schedule.clear();

        int numberOfModPatternPosters = 0;

        for (Poster poster : posters) {
            if (poster.getSchedule().isEmpty()) {
                numberOfModPatternPosters++;
            }
        }

        // int length = posters.size() - 1;
        int length = numberOfModPatternPosters;
        // int length = 4;
        int k = 0;
        LocalDateTime currentTime = Schedule.now();
        for (Poster poster : posters) {
            if (poster.getSchedule().isEmpty()) {
                // TemporalExpression pattern = new ModPattern(Schedule.now(), k++, length);
                TemporalExpression pattern = new ModPattern(Schedule.now(), length, k++);
                schedule.add(poster, pattern);
            } else {
                // schedule.add(poster.getSchedule());
                schedule.add(poster.getSchedule().getEvents());
            }
        }
    }

    /*
    public void run() {
        super.run();
        // schedule();
    }

    public void do() {
        Collection<Poster> posters = getSourcePosters();
        for (Poster poster : posters) {
            poster.move(this);
            schedule.add(poster.schedule); // operation should be equivalent to adding an array to another array
        }
    }

    public void stop() {
        super.stop();
    }
    */

    /*
    public void schedule(Timer timer) {
        timer.scheduleAtFixedRate(() -> activate(poster), Scheduler.time(0, 0, 0), Scheduler.year());
    }
    */
    // abstract protected void schedule();

    /**
     * Sets this poster's contents to the contents of the given poster.
     */
    public void activate(Poster poster) {
        // poster.move(this);
        poster.run();
    }

    public void move(Poster poster) { // ideally should only have to override one of these
        super.move(poster);
    }

    public void move(String path) {
        super.move(path);
    }

    /**
     * Removes extraneous files from this folder's directory.
     */
    public void cleanup() {

    }

    abstract protected List<Poster> createSourcePosters(); // really have ought to change how this works
}
