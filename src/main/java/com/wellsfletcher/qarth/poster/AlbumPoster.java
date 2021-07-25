package com.wellsfletcher.qarth.poster;

import java.util.List;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class AlbumPoster extends SpotifyPoster {
    String albumId;

    public AlbumPoster(String hostURL, String contentDirectory, String name, String albumId, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
        this.albumId = albumId;
        
        links = createLinks();
    }

    @Override
    protected List<String> createLinks() {
        List<String> links = spotify.getAlbumTrackUrls(albumId);
        return links;
    }

    /*
    @Override
    protected String getLink(int index) {
        List<String> links = spotify.getAlbumTrackUrls(albumId);
        return links.get(index % links.size());
    }
    */
}
