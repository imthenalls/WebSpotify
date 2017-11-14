/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.service.ArtistService;
import java.util.Arrays;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArtistServiceHibernateImpl implements ArtistService{
    @Autowired
    private ArtistDAO artistDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Artist getArtist(int id) {
        Artist artist = artistDao.getArtist(id);
        if(artist == null)
            return null;
        return artist;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewArtist(String artistName) {
        Artist artist = new Artist(artistName);
        artistDao.addArtist(artist);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Artist> listAllArtists()
    {
        List<Artist> listArtists = artistDao.listArtists();
        
        return listArtists;
    }
    
}
