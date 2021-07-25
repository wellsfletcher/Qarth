package com.wellsfletcher.qarth.poster;
import com.wellsfletcher.qarth.spotify.SpotifyManager;

import java.util.List;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class SpotifyPoster extends RedirectPoster {
    protected SpotifyManager spotify;

    /*
    public SpotifyPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int columns) {
        this(hostURL, contentDirectory, name, inputFilePath, 0, columns);
        setCountToLineCount();
    }

    public SpotifyPoster(String hostURL, String contentDirectory, String name, String inputFilePath, int rows, int columns) {
        super(hostURL, contentDirectory, name, inputFilePath, rows, columns);
        spotify = new SpotifyManager();
    }
    */

    public SpotifyPoster(String hostURL, String contentDirectory, String name, int rows, int columns) {
        super(hostURL, contentDirectory, name, "", rows, columns);
        spotify = new SpotifyManager();
    }

    /*
    @Override
    protected List<String> createLinks() {
        spotify = new SpotifyManager();
        return null;
    }
    */
}
