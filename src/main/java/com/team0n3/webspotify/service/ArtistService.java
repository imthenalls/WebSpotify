
package com.team0n3.webspotify.service;
 
import com.team0n3.webspotify.model.Artist; 
import java.util.List;
 
  public interface ArtistService {
      
    public Artist getArtist(int artistId);
    
    public void addNewArtist(String artistName); 
    
    public List<Artist> listAllArtists();
    
    public List<Artist> search(String keyword);
    
    public void updatePopularity(int artistId);
 }
