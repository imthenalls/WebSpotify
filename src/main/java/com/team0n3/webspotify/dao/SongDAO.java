
package com.team0n3.webspotify.dao;

import com.team0n3.webspotify.model.Song;
import java.util.List;

public interface SongDAO {
  
  public void addSong(Song song);

  public Song getSong(int id);
  
  public List<Song> listSongs();
  
  public void updateSong(Song song);
  
  public void deleteSong(Song song);
  
  public void mergeSong(Song song);
  
  public List<Song> search(String keyword);
}
