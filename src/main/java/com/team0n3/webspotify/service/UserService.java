
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import java.util.List;

public interface UserService {
  
  public User login(String username, String password);
  
  public User signup(String username, String password, String email);
  
  public List<Playlist> getCreated(String username);
  
  public void followPlaylist(String userId, int playlistId);
  
  public void unfollowPlaylist(String userId, int playlistId);
  
  public List<User> listAllUsers();

  public List<User> search(String keyword);
  public void followArtist(String userId, int artistId);
  public void unfollowArtist(String userId, int artistId);
   public void followSong(String userId, int songId);
    public void unfollowSong(String userId, int songId);
  public void followAlbum(String userId, int albumId);
  public void unfollowAlbum(String userId, int albumId);
  public void AddArtistAdmin(String username, String artistName,int popularity, String imagePath);
}
