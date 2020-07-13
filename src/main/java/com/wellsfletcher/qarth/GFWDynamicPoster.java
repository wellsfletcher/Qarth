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
public class GFWDynamicPoster extends DynamicPoster {
    protected String inputDirectory;

    protected String getInputPath() {
        return inputDirectory + name + DELIM;
    }

    public GFWDynamicPoster() {
        // https://wellsfletcher.com/qr/b/
        // https://wellsfletcher.com/qr/b/sources/
        // .../New-Personal-Website/qr/b/
        // .../New-Personal-Website/qr/b/sources/
        super("https://wellsfletcher.com/qr/",
            "/Users/wellsfletcher/Documents/OtherCode/HTML/New-Personal-Website/qr/",
            "h", // name
            5, // rows
            5 // columns
        );

        inputDirectory = "/Users/wellsfletcher/Documents/OtherCode/Java/Inputs/qr/";
        FileSystem.makeDirectory(inputDirectory);
    }

    protected Set<Poster> getSourcePosters() {
        Set<Poster> result = new HashSet();

        String inputDir = getInputPath();
        String url = getResourcesURL();
        String path = getResourcesPath();

        String posterName;
        String inputFileName;
        String inputFilePath;

        posterName = "phantom";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster phantom = new FilePoster(url, path, posterName, inputFilePath, columns);

        posterName = "bee";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster bee = new FilePoster(url, path, posterName, inputFilePath, columns);

        posterName = "fun-links";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster funLinks = new RedirectPoster(url, path, posterName, inputFilePath, columns);


        result.add(phantom);
        result.add(bee);
        result.add(funLinks);

        return result;
    }
}
