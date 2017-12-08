
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.Collection;
import java.util.List;

public interface UserService {
  
  public User login(String username, String password);
  public String signup(String username, String password, String email, boolean isArtist);
  public void addUser(String username, String password, String email, boolean isArtist);
  public List<Playlist> getCreatedPlaylists(String username);
  public List<User> listAllUsers();
  public User getUser(String username);
  public List<User> search(String keyword, boolean limit);
  public User followPlaylist(String userId, int playlistId);
  public User unfollowPlaylist(String userId, int playlistId);
  public User followArtist(String userId, int artistId);
  public User unfollowArtist(String userId, int artistId);
  public User followSong(String userId, int songId);
  public User unfollowSong(String userId, int songId);
  public User followAlbum(String userId, int albumId);
  public User unfollowAlbum(String userId, int albumId);
  public void artistCheckSongMetrics(String username, int artistId);
  public void changeProfilePic(String username, String path);
  public void adminAddSong(String title );
  public void adminAddAlbum(String albumName, int popularity, String imagePath );
  public void adminApproveFreeUser(String approve);
  public void adminApproveArtistUser(String approve); 
  public void adminDeleteUser(User u);
  public void adminDeleteArtist(Artist a);
  public void adminDeleteAlbum(Album a);
  public void adminDeleteSong(Song s);
  public void adminDeletePlaylist(Playlist p);
  public void adminSendRoyaltyChecks(String artistId);
  public boolean removeUser(String username, String password);
  public Collection<User> viewPlaylistFollowers(int playlistId);
  public void banUser(String username); 
  public User followUser(String username, String follow);
  public User unfollowUser(String username, String follow);
}
