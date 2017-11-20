package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Album;
import java.util.List;

public interface AlbumService {
    public Album getNewAlbum(int id);
    public void addNewAlbum(String albumName); 
    public List<Album> listAllAlbums();
}
