
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.service.ArtistService;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("artistService")
@Transactional(readOnly = true)
public class ArtistServiceHibernateImpl implements ArtistService{
    
    @Autowired
    private ArtistDAO artistDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    
    public Artist getArtist(int artistId) {
        Artist artist = artistDao.getArtist(artistId);
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
    @Transactional(readOnly = true)
    @Override
    public List<Artist> search(String keyword)
    {
      List<Artist> listArtists = artistDao.search(keyword);
      return listArtists;
    }
}