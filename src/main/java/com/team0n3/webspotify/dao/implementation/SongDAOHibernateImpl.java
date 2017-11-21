
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Song;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
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
        return (Song)sessionFactory.getCurrentSession().load(Song.class, id);
    }

    @Override
    public List<Song> listSongs() {
        List<Song> songList = sessionFactory.getCurrentSession().createCriteria(Song.class).list();
        ListIterator<Song> litr = null;
        litr=songList.listIterator();
        return songList;
    }

    @Override
    public void updateSong(Song song) {
        sessionFactory.getCurrentSession().update(song);
    }

    @Override
    public void deleteSong(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
