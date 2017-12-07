
package com.team0n3.webspotify.service;
 
import com.team0n3.webspotify.model.Artist; 
import com.team0n3.webspotify.model.Song;
import java.util.Collection;
import java.util.List;
import java.util.Collection;
 
  public interface ArtistService {
    public Artist getArtist(int artistId);
    public void addNewArtist(String artistName); 
    public List<Artist> listAllArtists();
    public List<Artist> search(String keyword, boolean limit);
    
    public void updatePopularity(int artistId);
    
    public void calcTotalRoyalties(int artistId);
    
    public Collection<Song> getSongsWithPlays(int artistId);

    public void artistRequestSongRemoval(int songId, int artistId);
    
    public List<Song> getSongRemovalRequests();
 }
