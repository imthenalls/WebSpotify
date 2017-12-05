
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.List;

public interface UserService {
  
  public User login(String username, String password);
  
  public String signup(String username, String password, String email);
  
  public List<Playlist> getCreatedPlaylists(String username);
  
  public void followPlaylist(String userId, int playlistId);
  
  public void unfollowPlaylist(String userId, int playlistId);
  
  public List<User> listAllUsers();
  public User getUser(String username);
  public List<User> search(String keyword);
  public void followArtist(String userId, int artistId);
  public void unfollowArtist(String userId, int artistId);
  public void followSong(String userId, int songId);
  public void unfollowSong(String userId, int songId);
  public User followAlbum(String userId, int albumId);
  public User unfollowAlbum(String userId, int albumId);
  
  
  public void adminAddArtist(String artistName,int popularity, String imagePath);
  public void adminAddPlaylist(String user, String playlistName,String imagePath, String description);
  public void adminAddSong(String title );
  public void adminEditSong(int songId);
  public void adminAddAlbum(String albumName, int popularity, String imagePath );
  public void adminEditArtistBio( int artistId);

  public void adminApproveFreeUser(String approve);
  public void adminApproveArtistUser(String approve);
  
  public void adminDeleteUser(User u);
  public void adminDeleteArtist(Artist a);
  public void adminDeleteAlbum(Album a);
  public void adminDeleteSong(Song s);
  public void adminDeletePlaylist(Playlist p);
}
