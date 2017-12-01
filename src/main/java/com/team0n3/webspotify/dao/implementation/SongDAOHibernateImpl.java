
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Song;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class SongDAOHibernateImpl implements SongDAO{   
  
  @Autowired
  private SessionFactory sessionFactory;
  
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
  public List<Song> search(String keyword){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Song.class);
    c.add(Restrictions.like("title", "%"+keyword+"%"));
    return c.list();
  }
}
