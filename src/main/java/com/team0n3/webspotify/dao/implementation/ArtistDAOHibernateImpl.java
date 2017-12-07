package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
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
    
    @Value("${artist.chartsResults}")
    private int chartsResults;
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
    
    @Override
    public List<Song> genre(String genre){
      SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from artistgenres");
      System.out.println("hello genre in artistdao");
      List<Object[]> rows = query.list();
      List<String> artistsInGenre = new ArrayList();
      for(Object[] row : rows){
        if(row[3].toString().equals(genre)){
          artistsInGenre.add(row[1].toString());
          System.out.println(row[1].toString());
        }
      }
      List<Song> allSongs = new ArrayList();
      for(String a : artistsInGenre){
        Integer result = Integer.valueOf(a);
        int artistId = result;
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Song.class);
        cr.add(Restrictions.eq("artistId.artistId", artistId));
        List<Song> songsInArtist = cr.list();
        allSongs.addAll(songsInArtist);
      }
      List<Song> top50Songs = new ArrayList();
      Song least = null;
      Song most = null;
      for(Song s : allSongs){
        if(least != null && most != null){
          if(s.getTotalPlays() > least.getTotalPlays()){
            if(top50Songs.size() == 50){//more than least but no space
              top50Songs.remove(least);
              top50Songs.add(s);
              least = most;//now need to update for a new least in top50
              for(Song s1 : top50Songs){
                if(s1.getTotalPlays() < least.getTotalPlays())
                  least = s1;
              }
            }else
              top50Songs.add(s); // more than least but still sapce for it
            if(s.getTotalPlays() > most.getTotalPlays())
              most = s;//check for new most
          }else if(top50Songs.size() != 50){//less than least but still space for it
            top50Songs.add(s);
            least = s;
          }
        }else{
          least = s;
          most = s;
          top50Songs.add(s);
        }
      }
      Collections.sort(top50Songs, new Comparator<Song>() {
        @Override
        public int compare(Song s1, Song s2) {
          return  s1.getTotalPlays() - s2.getTotalPlays();
            }
       });
      Collections.reverse(top50Songs);
      for(Song s : top50Songs)
        System.out.println(s.toString()+"-"+s.getArtistId().getArtistName()+"-"+s.getTotalPlays());
      return top50Songs;
    }       

  @Override
  public List<Artist> getTopArtists() {
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Artist.class);
    c.setMaxResults(chartsResults);
    c.addOrder(Order.desc("popularity"));
    return c.list();
  }
  
}