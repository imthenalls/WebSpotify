
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Song;
import java.util.List;

public interface SongService {
  
  public Song getSong(int songId);
  
  public void addNewSong(String title); 
  
  public List<Song> listAllSongs();
  
  public Song addSongToPlaylist(int songId, int playlistId);
  
  public void deleteSongFromPlaylist(int playlistId, int songId);
  
  public List<Song> search(String keyword);
  
  public void incrementTotalPlays(int songId);
  
  public void updateFollowerCount(int songId);
  
  public List<Song> getTop50Songs();
}
