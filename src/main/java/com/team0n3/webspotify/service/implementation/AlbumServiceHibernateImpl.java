
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.AlbumService;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("albumService")
@Transactional(readOnly = true)
public class AlbumServiceHibernateImpl implements AlbumService{

  @Autowired
  private AlbumDAO albumDao;
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Album getAlbum(int albumId) {
    Album album = albumDao.getAlbum(albumId);
    if(album == null)
      return null;
    return album;
  }

  @Transactional(readOnly = false)
  @Override
  public void addNewAlbum(String albumName) {
    Album album = new Album(albumName);
    albumDao.addAlbum(album);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Album> listAllAlbums()
  {
    List<Album> listAlbums = albumDao.listAlbums();
    return listAlbums;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Song> getAllSongsInAlbum(int albumId)
  {
    Album album = albumDao.getAlbum(albumId);
    if(album == null)
      return null;
    Collection<Song> SongsInAlbum = album.getSongs();
    List<Song> songList = new ArrayList(SongsInAlbum);
    return songList;
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<Album> search(String keyword, boolean limit){
      List<Album> listAlbums = albumDao.search(keyword, limit);
      return listAlbums;
  }
  
  @Override
  @Transactional(readOnly = true)
  public Collection<User> viewFollowers(int albumId){
    Album a = albumDao.getAlbum(albumId);
    return a.getFollowers();
  }
  
}
