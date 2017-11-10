/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;
import java.util.List;
import com.team0n3.webspotify.dao.ArtistDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.Artist;
import org.hibernate.Query;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author JSCHA
 */
public class ArtistDAOHibernateImpl implements ArtistDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public ArtistDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addArtist(Artist artist) {
        sessionFactory.getCurrentSession().persist(artist);
    }

    @Override
    public Artist getArtist(String artistName) {
        return (Artist)sessionFactory.getCurrentSession().load(Artist.class, artistName);
    }

    @Override
    public List<Artist> listArtists(){
        List<Artist> artistList = sessionFactory.getCurrentSession().createCriteria(Artist.class).list();
        ListIterator<Artist> litr = null;
        litr=artistList.listIterator();
         while(litr.hasNext()){
            System.out.println(litr.next().toString());
        }
        return artistList;
    }

    @Override
    public void updateArtist(Artist artist){
        sessionFactory.getCurrentSession().update(artist);
    }

    @Override
    public void deleteArtist(String artistName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
