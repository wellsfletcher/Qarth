package com.wellsfletcher.qarth;
import com.wellsfletcher.qarth.util.*;

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
public class EmptyPoster extends GridPoster {

    public EmptyPoster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 5, 4);
    }

    public EmptyPoster(String hostURL, String contentDirectory, String name, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
    }

    protected String getFileContent(int index) {
        return "";
    }
}
