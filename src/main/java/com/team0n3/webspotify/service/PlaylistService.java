
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import java.util.List;

public interface PlaylistService {
    public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser);
    
    public List<Playlist> listAllPlaylists();
    
    public Playlist getPlaylistByID(int playlistID);
    
    public Playlist AddSongToPlaylist(int songId, int playlistId);
    
    public List<Song> getSongsInPlaylists(int playlistId);
}
