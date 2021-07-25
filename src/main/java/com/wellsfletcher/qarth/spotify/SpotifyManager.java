package com.wellsfletcher.qarth.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.IPlaylistItem;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.albums.GetSeveralAlbumsRequest;
import com.wrapper.spotify.requests.data.player.AddItemToUsersPlaybackQueueRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;


public class SpotifyManager {
    private String clientId = "1a31e9ab8b824360903a1e5a7d0b77f4";
    private String clientSecret = "02d242f7fc4d4475a03a64ae831efdc0";

    private SpotifyApi spotifyApi;
    private ClientCredentialsRequest clientCredentialsRequest;

    public SpotifyManager() {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        clientCredentialsRequest = spotifyApi.clientCredentials().build();
        setAccessToken();
    }

    public void setAccessToken() {
        spotifyApi.setAccessToken(getAccessToken());
    }

    public String getAccessToken() {
        String result = "";
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            result = clientCredentials.getAccessToken();

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAlbumTrackUrls(String albumId) {
        List<String> result = new LinkedList<>();
        try {
            GetAlbumsTracksRequest getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(albumId).build();
            final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

            for (TrackSimplified track : trackSimplifiedPaging.getItems()) {
                // System.out.println("Name: " + track.getName() + ", " + track.getHref());
                Map<String, String> urlMap = track.getExternalUrls().getExternalUrls();
                Collection<String> urls = urlMap.values();
                for (String url : urls) {
                    System.out.println(url);
                    result.add(url);
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public List<String> getPlaylistTrackUrls(String playlistId) {
        List<String> result = new LinkedList<>();
        try {
            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistId).build();
            final Paging<PlaylistTrack> trackSimplifiedPaging = getPlaylistsItemsRequest.execute();

            for (PlaylistTrack item : trackSimplifiedPaging.getItems()) {
                IPlaylistItem itemItem = item.getTrack();
                if (itemItem.getType() == ModelObjectType.TRACK) {
                    Track track = (Track) itemItem;
                    // System.out.println("Name: " + track.getName() + ", " + track.getHref());
                    Map<String, String> urlMap = track.getExternalUrls().getExternalUrls();
                    Collection<String> urls = urlMap.values();
                    for (String url : urls) {
                        System.out.println(url);
                        result.add(url);
                    }
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public List<Track> getTracksFromPlaylist(String playlistId) {
        List<Track> result = new LinkedList<>();
        try {
            GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistId).build();
            final Paging<PlaylistTrack> trackSimplifiedPaging = getPlaylistsItemsRequest.execute();

            for (PlaylistTrack item : trackSimplifiedPaging.getItems()) {
                IPlaylistItem itemItem = item.getTrack();
                if (itemItem.getType() == ModelObjectType.TRACK) {
                    Track track = (Track) itemItem;
                    result.add(track);
                }
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    /**
     * Requires special authorization to work.
     * @param trackUri the Spotify uri of the track to be queued
     */
    public void queueTrack(String trackUri) {
        AddItemToUsersPlaybackQueueRequest addItemToUsersPlaybackQueueRequest = spotifyApi
                .addItemToUsersPlaybackQueue(trackUri)
                .build();
        try {
            final String string = addItemToUsersPlaybackQueueRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getAlbumCoverUrl(String albumId) {
        String result = "";
        GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(albumId).build();
        try {
            Album album = getAlbumRequest.execute();
            Image[] images = album.getImages();
            /*
            for (Image image : images) {
                String url = image.getUrl();
                System.out.println(url);
            }
            */
            String large = images[0].getUrl();
            // String medium = images[1].getUrl();
            // String small = images[2].getUrl();
            result = large;


            // System.out.println("Name: " + album.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAlbumCoverUrls(List<String> albumIds) {
        List<String> result = new LinkedList<>();
        String[] albumsIdsAsArray = albumIds.toArray(new String[1]);
        GetSeveralAlbumsRequest getAlbumsRequest = spotifyApi.getSeveralAlbums(albumsIdsAsArray).build();
        try {
            Album[] albums = getAlbumsRequest.execute();
            for (Album album : albums) {
                Image[] images = album.getImages();
                String large = images[0].getUrl();
                // String medium = images[1].getUrl();
                // String small = images[2].getUrl();
                result.add(large);
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAlbumCoverUrlsFromPlaylist(String playlistId) {
        List<String> result = new LinkedList<>();
        List<Track> tracks = getTracksFromPlaylist(playlistId);
        for (Track track : tracks) {
            AlbumSimplified simp = track.getAlbum();
            String albumId = simp.getId();
            String url = getAlbumCoverUrl(albumId);
            result.add(url);
        }
        return result;
    }

    public Album getAlbum(String id) {
        Album result = null;
        GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id).build();
        try {
            Album album = getAlbumRequest.execute();
            result = album;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    protected Album albumSimplifiedToAlbum(AlbumSimplified simp) {
        String id = simp.getId();
        Album result = getAlbum(id);
        return result;
    }

    public static void main(String[] args) {
        SpotifyManager spotify = new SpotifyManager();
        // spotify.getAlbumTrackUrls(Album.COSTELLO_MUSIC);
        // spotify.getPlaylistTrackUrls(Playlist.MMMM_LIST);
        // spotify.queueTrack(Tracks.NEVER_GONNA_GIVE_YOU_UP);
        System.out.println(spotify.getAlbumCoverUrl(Albums.ANGLES));
    }
}
