package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistDAOHibernateImpl implements ArtistDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public ArtistDAOHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    
    @Override
    public void addArtist(Artist artist){
        sessionFactory.getCurrentSession().persist(artist);
    }
    
    @Override
    public Artist getArtist(int artistId){
        Artist artist = (Artist)sessionFactory.getCurrentSession().get(Artist.class,artistId);
        return artist;
    }
    
    @Override
    public List<Artist> listArtists(){
        List<Artist> artistList = sessionFactory.getCurrentSession().createCriteria(Artist.class).list();
        return artistList;
    }
    
    @Override
    public void deleteArtist(Artist artist){
        sessionFactory.getCurrentSession().delete(artist);
    }
    
    @Override
    public void updateArtist(Artist artist){
        sessionFactory.getCurrentSession().update(artist);
    }
    
}