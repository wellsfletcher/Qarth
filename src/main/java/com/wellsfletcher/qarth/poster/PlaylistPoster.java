package com.wellsfletcher.qarth.poster;

import java.util.List;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class PlaylistPoster extends SpotifyPoster {
    String playlistId;

    public PlaylistPoster(String hostURL, String contentDirectory, String name, String albumId, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
        this.playlistId = albumId;

        links = createLinks();
    }

    @Override
    protected List<String> createLinks() {
        List<String> links = spotify.getPlaylistTrackUrls(playlistId);
        return links;
    }

    /*
    @Override
    protected String getLink(int index) {
        List<String> links = spotify.getPlaylistTrackUrls(playlistId);
        return links.get(index % links.size());
    }
    */
}
