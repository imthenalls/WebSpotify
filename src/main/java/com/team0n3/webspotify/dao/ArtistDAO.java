
package com.team0n3.webspotify.dao;

import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Song;
import java.util.List;

public interface ArtistDAO {
    public void addArtist(Artist artist);
    public Artist getArtist(int artistId);
    public List<Artist> listArtists();
    public void deleteArtist(Artist artist);
    public void updateArtist(Artist artist);
    public List<Artist> search(String keyword, boolean limit);
    public List<Song> getGenrePlaylist(String genre);
    public List<Artist> getTopArtists();
    public List<Artist> getNewArtists();
    public List<Artist> getNotFollowedArtists(String username);
}