package com.wellsfletcher.qarth.util;

// general
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

// data structures
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Contains functionality for manipulating files.
 */
public final class FileSystem {
    public static final String DELIMITER = "/";

    private FileSystem() {}

    public static String join(String directory, String name) {
        return Paths.get(directory, name).toString();
    }

    public static String getExtension(String path) {
        return getExtension(new File(path));
    }

    public static String getExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".") + 1;
        if (lastIndexOf == 0) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public static String includeTrailingSlash(String path) {
        return path.endsWith(DELIMITER) ? path : path + DELIMITER;
    }

    public static String excludeTrailingSlash(String path) {
        if (path.endsWith(DELIMITER)) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    public static void makeDirectory(String path) {
        makeDirectory(new File(path));
    }

    public static void makeDirectory(File directory) {
        if (!directory.exists()) {
            try {
                directory.mkdir();
            } catch (SecurityException se) {

            }
        }
    }

    public static int countLines(String path) {
        return countLines(new File(path));
    }

    public static int countLines(File file) {
        return getLinesAsList(file).size();
    }

    public static String[] getLinesAsArray(String path) {
        return getLinesAsArray(new File(path));
    }

    public static String[] getLinesAsArray(File file) {
        List<String> lines = getLinesAsList(file);
        return lines.toArray(new String[0]);
    }

    public static List<String> getLinesAsList(String path) {
        return getLinesAsList(new File(path));
    }

    public static List<String> getLinesAsList(File file) {
        List<String> lines = null;
        try {
            Scanner sc = new Scanner(file);
            lines = new ArrayList<String>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.equals("")) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
