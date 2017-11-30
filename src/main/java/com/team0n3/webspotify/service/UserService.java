
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
  
  public void followArtist(String userId, int artistId);
}
