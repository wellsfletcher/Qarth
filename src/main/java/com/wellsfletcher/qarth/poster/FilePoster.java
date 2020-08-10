package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.util.Scanner;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Represents a poster of QR codes, with a text file used as input.
 */
public class FilePoster extends GridPoster {
    private static final String DEFAULT_INPUT_FILE_NAME = "input.txt";
    protected String input;

    public FilePoster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, FileSystem.join(contentDirectory, DEFAULT_INPUT_FILE_NAME));
    }

    public FilePoster(String hostURL, String contentDirectory, String name, String inputFilePath) {
        this(hostURL, contentDirectory, name, inputFilePath, 4);
    }

    public FilePoster(String hostURL, String contentDirectory, String name, int columns) {
        this(hostURL, contentDirectory, name, FileSystem.join(contentDirectory, DEFAULT_INPUT_FILE_NAME), columns);
    }

    public FilePoster(String hostURL, String contentDirectory, String name, int rows, int columns) {
        this(hostURL, contentDirectory, name, FileSystem.join(contentDirectory, DEFAULT_INPUT_FILE_NAME), rows, columns);
    }

    /*
    public FilePoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        super(hostURL, contentDirectory, name, 0, columns);
        this.input = inputFilePath;
        this.count = FileSystem.countLines(input);
    }
    */

    public FilePoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        this(hostURL, contentDirectory, name, inputFilePath,0, columns);
        this.count = FileSystem.countLines(input);
    }

    public FilePoster(String hostURL, String contentDirectory, String name, String inputFilePath, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
        this.input = inputFilePath;
        // this.count = FileSystem.countLines(input);
    }

    public void create(int columns) {
        this.columns = columns;
        create();
    }

    protected String getFileContent(int index) {
        String path = input;
        String[] texts = FileSystem.getLinesAsArray(path);

        return texts[(index) % texts.length];
    }
}
