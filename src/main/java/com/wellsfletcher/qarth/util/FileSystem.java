package com.wellsfletcher.qarth.util;

// general
import java.io.*;
import java.nio.file.Paths;

/**
 * Contains functionality for manipulating files.
 */
public final class FileSystem {
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
        String DELIMITER = "/";
        return path.endsWith(DELIMITER) ? path : path + DELIMITER;
    }

    public static String excludeTrailingSlash(String path) {
        String DELIMITER = "/";
        if (path.endsWith(DELIMITER)) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }
}