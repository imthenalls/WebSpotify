package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ArtistDAOHibernateImpl implements ArtistDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Value("${artist.maxResult}")
    private int maxResults;
    
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
        Hibernate.initialize(artist.getAlbums());
        Hibernate.initialize(artist.getSongs());
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
    
    @Override
    public List<Artist> search(String keyword, boolean limit){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Artist.class);
    c.add(Restrictions.like("artistName", "%"+keyword+"%"));
    if(limit){
      c.setMaxResults(maxResults);
    }
    c.addOrder(Order.desc("popularity"));
    return c.list();
  }

}