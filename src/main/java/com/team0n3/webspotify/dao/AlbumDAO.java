/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.Album;
/**
 *
 * @author spike
 */
public interface AlbumDAO {
    public void addAlbum(Album album);
    
    public Album getAlbum(int albumId);
    
    public List<Album> listAlbums();
    
    public void deleteAlbum(Album album);
    
    public void updateAlbum(Album album);
}
