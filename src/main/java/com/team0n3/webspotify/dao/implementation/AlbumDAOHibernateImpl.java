  
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.model.Album;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AlbumDAOHibernateImpl implements AlbumDAO{
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${album.maxResult}")
  private int maxResults;
  
  @Value("${album.chartsResult}")
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

}
