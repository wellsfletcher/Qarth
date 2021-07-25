package com.wellsfletcher.qarth.poster;

import java.util.List;

/**
 * Represents a poster of QR codes, with a text file of links used as the input.
 */
public class AlbumArtworkPoster extends SpotifyPoster {
    List<String> albumIds;

    public AlbumArtworkPoster(String hostURL, String contentDirectory, String name, List<String> albumIds, int rows, int columns) {
        super(hostURL, contentDirectory, name, rows, columns);
        this.albumIds = albumIds;
        
        links = createLinks();
    }

    @Override
    protected List<String> createLinks() {
        List<String> links = spotify.getAlbumCoverUrls(albumIds);
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
