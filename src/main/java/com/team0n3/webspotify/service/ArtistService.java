
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Artist;
import java.util.List;
import java.util.Collection;

public interface ArtistService {
    public Artist getArtist(int artistId);
    public void addNewArtist(String artistName); 
    public List<Artist> listAllArtists();
}
