/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.service.AlbumService;
import java.util.Arrays;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;

@Service("albumService")
@Transactional(readOnly = true)
public class AlbumServiceHibernateImpl implements AlbumService{
    @Autowired
    private AlbumDAO albumDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Album getNewAlbum(int id) {
        Album album = albumDao.getAlbum(id);
        if(album == null)
            return null;
        return album;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewAlbum(String albumName) {
        Album album = new Album(albumName);
        albumDao.addAlbum(album);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Album> listAllAlbums()
    {
        List<Album> listAlbums = albumDao.listAlbums();
        
        return listAlbums;
    }
    
}
