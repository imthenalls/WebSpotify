package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.Collection;
import java.util.List;

public interface AlbumService {
  
  public Album getAlbum(int albumId);
  
  public void addNewAlbum(String albumName); 
  
  public List<Album> listAllAlbums();
  
  public List<Song> getAllSongsInAlbum(int albumId);
  
  public List<Album> search(String keyword, boolean limit);
  
  public Collection<User> viewFollowers(int albumId);
  
  public List<Album> getTopAlbums();
  
  public List<Album> getNewAlbums();
  public List<Album> getNotFollowedAlbums(String username);
}
