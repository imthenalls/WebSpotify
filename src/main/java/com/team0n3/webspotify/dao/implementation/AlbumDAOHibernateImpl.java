/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;
import java.util.List;
import com.team0n3.webspotify.dao.AlbumDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.Album;
import org.hibernate.Query;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author JSCHA
 */
public class AlbumDAOHibernateImpl implements AlbumDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public AlbumDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addAlbum(Album album) {
        sessionFactory.getCurrentSession().persist(album);
    }

    @Override
    public Album getAlbum(int id) {
        return (Album)sessionFactory.getCurrentSession().load(Album.class, id);
    }

    @Override
    public List<Album> listAlbums(){
        List<Album> albumList = sessionFactory.getCurrentSession().createCriteria(Album.class).list();
        ListIterator<Album> litr = null;
        litr=albumList.listIterator();
         while(litr.hasNext()){
            System.out.println(litr.next().toString());
        }
        return albumList;
    }

    @Override
    public void updateAlbum(Album album){
        sessionFactory.getCurrentSession().update(album);
    }

    @Override
    public void deleteAlbum(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
