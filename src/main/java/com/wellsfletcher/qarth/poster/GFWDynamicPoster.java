package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.gen.Generator;
import com.wellsfletcher.qarth.poster.schedule.*;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.util.Scanner;
import java.time.*;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Manages daily dynamic poster
 */
public class GFWDynamicPoster extends PosterCollection {
    protected static String inputDirectory = "/Users/wellsfletcher/Documents/OtherCode/Java/Inputs/qr/";

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

        // inputDirectory = "/Users/wellsfletcher/Documents/OtherCode/Java/Inputs/qr/";
        FileSystem.makeDirectory(inputDirectory);
        // createSourcePosters();
    }

    protected List<Poster> createSourcePosters() { // as of now, may use uninitiallized values
        List<Poster> result = new ArrayList();

        String inputDir = getInputPath();
        String url = getResourcesURL();
        String path = getResourcesPath();

        String posterName;
        String inputFileName;
        String inputFilePath;

        Duration delay;
        TemporalExpression pattern;

        posterName = "phantom";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster phantom = new FilePoster(url, path, posterName, inputFilePath, rows, columns);
        pattern = new ModPattern(Schedule.now(), 3, 0);
        schedule.add(phantom, pattern);

        posterName = "bee";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster bee = new FilePoster(url, path, posterName, inputFilePath, rows, columns);
        // schedule.forTomorrow(() -> poster.run());
        // schedule.forTomorrow(bee); // this should occur elsewhere?
        delay = Duration.ofSeconds(10);
        schedule.afterDuration(bee, delay);

        posterName = "fun-links";
        inputFileName = posterName + ".txt";
        inputFilePath = inputDir + inputFileName;
        Poster funLinks = new RedirectPoster(url, path, posterName, inputFilePath, rows, columns);
        delay = Duration.ofSeconds(25);
        schedule.afterDuration(funLinks, delay);

        posterName = "fletcher";
        inputFileName = "birthdays/" + posterName + ".xml";
        inputFilePath = inputDir + inputFileName;
        Poster birth = new BirthdayPoster(url, path, posterName, inputFilePath, rows, columns);
        // schedule.add(birth.getSchedule());
        // merge(birth);

        result.add(birth);
        result.add(phantom);
        result.add(bee);
        result.add(funLinks);
        // result.add(birth);

        return result;
    }

    /*
    protected void schedule() {

    }
    */

    /*
    protected void add(Poster poster) {

    }
    */
}
