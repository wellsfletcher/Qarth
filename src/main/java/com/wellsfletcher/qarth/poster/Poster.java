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
 * Represents a poster of QR codes that link to a static html page.
 */
public abstract class Poster {
    protected static final String DELIM = FileSystem.DELIMITER;

    protected String name; // the name of the poster (should be short)
    protected String url; // the base url where the poster qr codes link to
    protected String content;

    protected int count; // number of QR codes

    protected String getContentPath() {
        return content + name + DELIM;
    }

    protected String getContentURL() {
        return url + name + DELIM;
    }

    public Poster(String hostURL, String contentDirectory, String name) {
        this(hostURL, contentDirectory, name, 0);
    }

    public Poster(String hostURL, String contentDirectory, String name, int count) {
        this.url = hostURL;
        this.content = contentDirectory;
        this.name = name;
        this.count = count;

        File dir = new File(getContentPath());

        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (SecurityException se) {

            }
        }
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

    abstract protected void image(List<String> links, String path);

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
            // esult += (char) (((int) curr) + shift);
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

    abstract protected String getFileContent(int index);
}
