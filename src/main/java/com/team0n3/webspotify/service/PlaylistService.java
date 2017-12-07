
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.Collection;
import java.util.List;

public interface PlaylistService {
  public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser);
  public List<Playlist> listAllPlaylists();
  public Playlist getPlaylist(int playlistID);
  public void deletePlaylist(Playlist p);
  public List<Song> getSongsInPlaylists(int playlistId);
  public void renamePlaylist(int playlistId, String playlistName);
  public List<Playlist> search(String keyword, boolean limit);
  public Playlist updatePlaylist(int id, String name, String path, String description);
  public Playlist getGenrePlaylist();
  public Playlist toggleCollab(int playlistID);
  public Playlist togglePublic(int playlistID);
  public List<Playlist> getTopPlaylists();
}
