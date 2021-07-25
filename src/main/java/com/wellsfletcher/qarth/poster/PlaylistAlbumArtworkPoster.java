package com.wellsfletcher.qarth.poster;

import java.util.List;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class PlaylistAlbumArtworkPoster extends SpotifyPoster {
    String playlistId;

    public PlaylistAlbumArtworkPoster(String hostURL, String contentDirectory, String name, String playlistId, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
        this.playlistId = playlistId;
        
        links = createLinks();
    }

    @Override
    protected List<String> createLinks() {
        List<String> links = spotify.getAlbumCoverUrlsFromPlaylist(playlistId);
        return links;
    }
}
