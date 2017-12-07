
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.Collection;
import java.util.List;

public interface SongService {
  
  public Song getSong(int songId);
  
  public void addNewSong(String title); 
  
  public List<Song> listAllSongs();
  
  public Song addSongToPlaylist(int songId, int playlistId);
  
  public void deleteSongFromPlaylist(int playlistId, int songId);
    
  public List<Song> search(String keyword, boolean limit);
  
  public void incrementTotalPlays(int songId);
  
  public void updateFollowerCount(int songId);
  
  public List<Song> getTop50Songs();
  
  public List<String> getGenreList();
  
  public Collection<User> viewFollowers(int songId);
  
}
