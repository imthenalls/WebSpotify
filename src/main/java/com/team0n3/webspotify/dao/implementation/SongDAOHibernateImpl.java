
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SongDAOHibernateImpl implements SongDAO{   
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${song.maxResult}")
  private int maxResults;
  
  public SongDAOHibernateImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addSong(Song song) {
    sessionFactory.getCurrentSession().persist(song);
  }

  @Override
  public Song getSong(int id) {
    Song song = (Song)sessionFactory.getCurrentSession().get(Song.class,id);
    Hibernate.initialize(song.getAlbumId());
    return song;
  }

  @Override
  public List<Song> listSongs() {
    List<Song> songList = sessionFactory.getCurrentSession().createCriteria(Song.class).list();
    
    return songList;
  }

  @Override
  public void updateSong(Song song) {
    sessionFactory.getCurrentSession().update(song);
  }
  
  @Override
  public void mergeSong(Song song) {
    sessionFactory.getCurrentSession().merge(song);
  }
  @Override
  public void deleteSong(Song song) {
    sessionFactory.getCurrentSession().delete(song);
  }
  
    
  @Override
  public List<Song> search(String keyword, boolean limit){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Song.class);
    c.add(Restrictions.like("title", "%"+keyword+"%"));
    if(limit){
      c.setMaxResults(maxResults);
    }
    c.addOrder(Order.desc("totalPlays"));
    return c.list();
  }

  @Override
  public List<Song> getTop50() {
   Criteria c = sessionFactory.getCurrentSession().createCriteria(Song.class);
    c.addOrder(Order.desc("totalPlays"));
    c.setMaxResults(50);
    List<Song> songs = c.list();

    return c.list();  
  }
  
  @Override
  public List<String> getGenreList(){
    SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from genres");
    List<Object[]> rows = query.list();
    List<String> genres = new ArrayList();
    for(Object[] row : rows)
      genres.add(row[1].toString());
    return genres;
  }
}
