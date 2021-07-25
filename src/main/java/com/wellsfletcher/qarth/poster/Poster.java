package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.util.time.schedule.Schedule;

import java.io.*;

import java.util.List;
import java.util.LinkedList;

/**
 * Represents a poster of QR codes that link to a static html page.
 */
public abstract class Poster implements Runnable {
    protected static final String DELIM = FileSystem.DELIMITER;

    protected String name; // the name of the poster (should be short)
    protected String url; // the base url where the poster qr codes link to
    protected String content;

    protected String contentPath;
    protected String contentURL;

    protected Schedule schedule;

    protected int count; // number of QR codes

    protected String getContentPath() {
        return contentPath;
    }

    protected String getContentURL() {
        return contentURL;
    }

    protected void setContentPath() {
        setContentPath(content + name + DELIM);
    }

    protected void setContentURL() {
        setContentURL(url + name + DELIM);
    }

    protected void setContentPath(String contentPath) {
        this.contentPath = FileSystem.includeTrailingSlash(contentPath);
    }

    protected void setContentURL(String contentURL) {
        this.contentURL = FileSystem.includeTrailingSlash(contentURL);
    }

    public Poster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 0);
    }

    public Poster(String hostURL, String contentDirectory, String name, int count) {
        this.url = hostURL;
        this.content = contentDirectory;
        this.name = name;
        this.count = count;
        setContentPath();
        getContentURL();

        File dir = new File(getContentPath());
        FileSystem.makeDirectory(dir);
        /*
        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (SecurityException se) {

            }
        }
        */

        this.schedule = new Schedule();
        // setup();
        // schedule();
    }

    public void create(int count) {
        this.count = count;
        create();
    }

    public void create() {
        List links = new LinkedList<>();

        for (int k = 0; k < count; k++) {
            String fileName = getFileName(k);
            file(fileName, k);
            String link = FileSystem.join(getContentURL(), fileName);
            links.add(link);
        }

        String name = "code.png";
        String path = FileSystem.join(getContentPath(), name);
        image(links, path);
    }

    /*
    public void create(String path) {

    }
    */

    /**
     * Runs the poster, so it's html content may be modified dynamically.
     */
    public void run() {
        System.out.println("Running '" + name + "'.");
        create();
        // schedule.run();
    }

    protected void schedule() {
        // schedule.forNow(() -> do()); // do now until forever
    }

    protected Schedule getSchedule() {
        return schedule;
    }

    /*
    protected void merge(Poster poster) {
        this.schedule.add(poster.getSchedule());
    }
    */

    /*
    public void start() {
        // create();
        schedule();
        schedule.start();
    }

    protected void schedule() {
        schedule.forNow(() -> do()); // do now until forever
    }

    protected void do() {
        stop();
    }
     */

    /**
     * Stops the poster from running.
     *
    public void stop() {
        schedule.stop();
    }
     */

    protected String getFileName(int index) {
        String result = "";
        final int PADDING = 3;
        result += encryptName(String.format("%0" + PADDING + "d", index));
        result += ".html";
        return result;
    }

    protected static String encryptName(String input) {
        String result = "";
        int SHIFT = 3 * 4;

        int A = 'a';
        int ZERO = '0';
        int ALPHABET_LENGTH = 26;
        for (int k = 0; k < input.length(); k++) {
            char curr = input.charAt(k);
            int value = curr;
            curr = (char) (((value - ZERO + SHIFT) % ALPHABET_LENGTH) + A);
            // curr = (char) ((currValue - (int) '0') + (int) 'a' + 3) % 26;
            // result += (char) (((int) curr) + shift);
            result += curr;

        }
        return result;
    }

    /**
     * Creates an html page with the specified name.
     */
    protected void file(String name, int index) {
        File file = new File(getContentPath() + name);
        try  {
            file.createNewFile();
            FileWriter out = new FileWriter(file);
            out.write(getFileContent(index));
            out.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Copies the posters contents to a different directory.
     * @requires the poster to have already been created
     */
    public void copy(String path) {
        String source = getContentPath();
        String destination = path;
        String extension = "html";
        FileSystem.copyFilesWithExtension(source, destination, extension);
    }

    public void move(Poster poster) {
        content = poster.content;
        // url = poster.url;
        setContentPath(poster.getContentPath());
        setContentURL(poster.getContentPath());
    }

    /**
     * Retains string name.
     */
    public void move(String path) {
        this.content = path;
        setContentPath(path);
        setContentURL();
    }

    public String toString() {
        String result = "";
        result += name;
        return result;
    }

    abstract protected void image(List<String> links, String path);

    abstract protected String getFileContent(int index);
}
