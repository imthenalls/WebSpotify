/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;

import java.util.List;
import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.model.Playlist;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author spike
 */
public class PlaylistDAOHibernateImpl implements PlaylistDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public PlaylistDAOHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    
    @Override
    public void addPlaylist(Playlist playlist){
        sessionFactory.getCurrentSession().persist(playlist);
    }
    
    @Override
    public Playlist getPlaylist(int playlistID){
        return (Playlist)sessionFactory.getCurrentSession().load(Playlist.class,playlistID);
    }
    
    @Override
    public List<Playlist> listPlaylists(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void deletePlaylist(Playlist playlist){
        sessionFactory.getCurrentSession().delete(playlist);
    }
}
