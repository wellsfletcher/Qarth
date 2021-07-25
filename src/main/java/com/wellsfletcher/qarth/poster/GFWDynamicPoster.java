package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.spotify.Albums;
import com.wellsfletcher.qarth.spotify.Playlists;
import com.wellsfletcher.qarth.util.*;
import com.wellsfletcher.qarth.util.time.pattern.ModPattern;
import com.wellsfletcher.qarth.util.time.pattern.TemporalExpression;
import com.wellsfletcher.qarth.util.time.schedule.*;

import java.time.*;

import java.util.List;
import java.util.ArrayList;

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
            "/Users/wellsfletcher/Documents/OtherCode/HTML/PersonalWebsite/qr/",
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

        /*
        posterName = "fletcher";
        inputFileName = "birthdays/" + posterName + ".xml";
        inputFilePath = inputDir + inputFileName;
        Poster birth = new BirthdayPoster(url, path, posterName, inputFilePath, rows, columns);
        // schedule.add(birth.getSchedule());
        // merge(birth);
        */

        posterName = "albums";
        Poster albums = new AlbumPoster(url, path, posterName, Albums.COSTELLO_MUSIC, rows, columns);

        posterName = "mmmmList";
        Poster mmmmList = new PlaylistPoster(url, path, posterName, Playlists.MMMM_LIST, rows, columns);

        posterName = "album-art";
        // Poster albumArt = new AlbumArtworkPoster(url, path, posterName, Albums.valuesAsList(), rows, columns);
        Poster albumArt = new PlaylistAlbumArtworkPoster(url, path, posterName, Playlists.COLLEGE_ROOM, rows, columns);

        // result.add(birth);
        result.add(albumArt);
        result.add(mmmmList);
        result.add(albums);
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
