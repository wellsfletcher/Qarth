package com.wellsfletcher.qarth;
import com.wellsfletcher.qarth.util.*;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Manages daily dynamic poster
 */
public abstract class DynamicPoster extends EmptyPoster {
    private static final String SOURCES_FOLDER_NAME = "resources";

    public DynamicPoster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 5, 4);
    }

    public DynamicPoster(String hostURL, String contentDirectory, String name, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);

        FileSystem.makeDirectory(getResourcesPath());
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
        Set<Poster> posters = getSourcePosters();
        for (Poster poster : posters) {
            poster.create();
        }
    }

    abstract protected Set<Poster> getSourcePosters();
}
