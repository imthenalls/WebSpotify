/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;

import com.team0n3.webspotify.model.Song;
import java.util.List;

/**
 *
 * @author JSCHA
 */
public interface SongDAO {
    public void addSong(Song song);
    
    public Song getSong(int id);
    
    public List<Song> listSongs();
     
    public void updateSong(Song song);
     
    public void deleteSong(Song song);
}
