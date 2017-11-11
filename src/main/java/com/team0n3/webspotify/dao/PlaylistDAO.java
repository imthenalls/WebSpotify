/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.Playlist;

/**
 *
 * @author spike
 */
public interface PlaylistDAO {
    public void addPlaylist(Playlist playlist);
    
    public Playlist getPlaylist(int playlistID);
    
    public List<Playlist> listPlaylists();
    
    public void deletePlaylist(Playlist playlist);
}
