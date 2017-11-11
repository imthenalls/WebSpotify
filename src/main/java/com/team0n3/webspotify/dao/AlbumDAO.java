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
 * @author JSCHA
 */
public interface AlbumDAO {
    public void addAlbum(Album album);
    
    public Album getAlbum(int id);
    
    public List<Album> listAlbums();
     
    public void updateAlbum(Album album);
     
    public void deleteAlbum(int id);
}
