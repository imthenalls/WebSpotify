  
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.model.Album;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AlbumDAOHibernateImpl implements AlbumDAO{
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${album.maxResult}")
  private int maxResults;
  
  @Value("${album.chartsResults}")
  private int chartsResults;
  public AlbumDAOHibernateImpl(SessionFactory sessionFactory){
    this.sessionFactory=sessionFactory;
  }

  @Override
  public void addAlbum(Album album){
    sessionFactory.getCurrentSession().persist(album);
  }

  @Override
  public Album getAlbum(int albumId){
    Album album = (Album)sessionFactory.getCurrentSession().get(Album.class,albumId);
    Hibernate.initialize(album.getSongs());
    return album;
  }

  @Override
  public List<Album> listAlbums(){
    List<Album> albumList = sessionFactory.getCurrentSession().createCriteria(Album.class).list();
    return albumList;
  }

  @Override
  public void deleteAlbum(Album album){
    sessionFactory.getCurrentSession().delete(album);
  }

  @Override
  public void updateAlbum(Album album){
    sessionFactory.getCurrentSession().update(album);
  }
  
  @Override
  public List<Album> search(String keyword, boolean limit){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Album.class);
    c.add(Restrictions.like("albumName", "%"+keyword+"%"));
    if(limit){
      c.setMaxResults(maxResults);
    }
    return c.list();
  }

  @Override
  public List<Album> getTopAlbums() {
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Album.class);
    c.setMaxResults(chartsResults);
    c.addOrder(Order.desc("popularity"));
    return c.list();
  }
  
  @Override
  public List<Album> getNewAlbums() {
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Album.class);
    c.setMaxResults(chartsResults);
    c.addOrder(Order.desc("albumId"));
    return c.list();
  }
  
  public List<Album> getNotFollowedAlbums(String username){
    Criteria crit = sessionFactory.getCurrentSession().createCriteria(Album.class);
    Junction or = Restrictions.disjunction();
    or.add(Restrictions.isEmpty("followers"));
    crit.createAlias("followers", "fol", JoinType.LEFT_OUTER_JOIN);
    or.add(Restrictions.ne("fol.username", username));
    crit.add(or);
    crit.addOrder(Order.desc("popularity"));
    crit.setMaxResults(chartsResults);
    return crit.list();
  }

}
