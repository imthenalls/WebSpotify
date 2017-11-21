
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Song;
import java.util.List;

public interface SongService {
  
  public Song getSong(int songId);
  
  public void addNewSong(String title); 
  
  public List<Song> listAllSongs();
  
  public Song AddSongToPlaylist(int songId, int playlistId);
}
