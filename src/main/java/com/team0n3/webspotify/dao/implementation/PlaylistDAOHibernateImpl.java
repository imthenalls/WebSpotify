
package com.team0n3.webspotify.dao.implementation;

import java.util.List;
import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.model.Playlist;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

public class PlaylistDAOHibernateImpl implements PlaylistDAO{
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${playlist.maxResult}")
  private int maxResults;
  public PlaylistDAOHibernateImpl(SessionFactory sessionFactory){
    this.sessionFactory=sessionFactory;
  }

  @Override
  public void addPlaylist(Playlist playlist){
    sessionFactory.getCurrentSession().persist(playlist);
  }

  @Override
  public Playlist getPlaylist(int playlistID){
    Playlist p = (Playlist)sessionFactory.getCurrentSession().get(Playlist.class,playlistID);
    Hibernate.initialize(p.getSongs());
    Hibernate.initialize(p.getFollowers());
    return p;
  }

  @Override
  public List<Playlist> listPlaylists(){
    List<Playlist> playlistList = sessionFactory.getCurrentSession().createCriteria(Playlist.class).list();
    return playlistList;
  }

  @Override
  public void deletePlaylist(Playlist playlist){
    sessionFactory.getCurrentSession().delete(playlist);
  }
  
  @Override
  public void updatePlaylist(Playlist playlist) {
    sessionFactory.getCurrentSession().update(playlist);
  }
  @Override
  public List<Playlist> search(String keyword){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Playlist.class);
    c.add(Restrictions.like("playlistName", "%"+keyword+"%"));
    c.setMaxResults(maxResults);
    return c.list();
  }
}
