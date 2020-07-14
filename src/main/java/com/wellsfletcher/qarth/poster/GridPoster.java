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
 * Represents a poster of a grid of QR codes.
 */
public abstract class GridPoster extends Poster {
    protected int rows;
    protected int columns;

    public GridPoster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 5, 4);
    }

    public GridPoster(String hostURL, String contentDirectory, String name, int rows, int columns) {
        super(hostURL, contentDirectory, name);
        setDimensions(rows, columns);
    }

    public void create(int rows, int columns) {
        setDimensions(rows, columns);
        super.create();
    }

    protected void image(List<String> links, String path) {
        /*
        System.out.println("columns = " + columns
            + ", " + "rows = " + rows
            + ", " + "count = " + count
            + ", " + "path = " + path
        );
        */
        Generator.grid(links, path, columns);
    }

    protected String getFileContent(int row, int column) {
        int index = row * columns + column;
        return getFileContent(index);
    }

    protected void setDimensions(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.count = rows * columns;
    }
}
