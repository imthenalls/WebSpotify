
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PlaylistService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlaylistServiceHibernateImpl implements PlaylistService{
  
  @Autowired
  private PlaylistDAO playlistDao;
  
  @Autowired
  private UserDAO userDao;
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${playlist.maxDescription}")
  private int maxDescription;
  @Value("${playlist.maxName}")
  private int maxName;
  @Override
  @Transactional(readOnly = false)
  public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser) {
    Playlist playlist = new Playlist(playlistName,imagePath,description,currentUser);
    playlist.setIsPublic(true);
    playlistDao.addPlaylist(playlist);
    return playlist;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Playlist> listAllPlaylists(){
    return playlistDao.listPlaylists();
  }

  @Override
  @Transactional(readOnly = true)
  public Playlist getPlaylist(int playlistID){
    return playlistDao.getPlaylist(playlistID);
  }

  @Override
  @Transactional(readOnly = false)
  public void deletePlaylist(Playlist p){
    playlistDao.deletePlaylist(p);
  }
 
  @Override
  @Transactional(readOnly = true)
  public List<Song> getSongsInPlaylists(int playlistId){
    Playlist playlist = playlistDao.getPlaylist(playlistId);
    if(playlist == null)
      return null;
    Collection<Song> songsInPlaylist = playlist.getSongs();
    List<Song> songList = new ArrayList(songsInPlaylist);
    return songList;
  }
  
  @Override
  @Transactional(readOnly = false)
  public void renamePlaylist(int playlistId, String playlistName){
      Playlist playlist = playlistDao.getPlaylist(playlistId);
       System.out.println("pre helo rename "+playlist.getPlaylistName());
      playlist.setPlaylistName( playlistName);
      playlistDao.updatePlaylist( playlist);
      System.out.println("hello rename "+playlist.getPlaylistName());
  }
  @Override
  public List<Playlist> search(String keyword, boolean limit){
    List<Playlist> listPlaylist = playlistDao.search(keyword, limit);
    return listPlaylist;
  }
  
  @Override
  @Transactional(readOnly = false)
  public Playlist updatePlaylist(int id, String name, String path, String description){
    Playlist playlist = playlistDao.getPlaylist(id);
    if(!path.isEmpty()){
      playlist.setImagePath(path);
    }
    if(!name.isEmpty() && name.length()<maxName){
      playlist.setPlaylistName(name);
    }
    if(!description.isEmpty() && description.length()<maxDescription){
      playlist.setDescription(description);
    }
    playlistDao.updatePlaylist(playlist);
    return playlist;
  }
  
  @Override
  @Transactional(readOnly = true)
  public Playlist getGenrePlaylist(){
    Playlist playlist = new Playlist();
    return playlist;
  }

  @Override
  @Transactional(readOnly = false)
  public Playlist toggleCollab(int playlistID) {
    Playlist playlist=playlistDao.getPlaylist(playlistID);
    playlist.setIsCollaborative(!playlist.getIsCollaborative());
    playlistDao.updatePlaylist(playlist);
    return playlist;
  }

  @Override
  @Transactional(readOnly = false)
  public Playlist togglePublic(int playlistID) {
    Playlist playlist=playlistDao.getPlaylist(playlistID);
    playlist.setIsPublic(!playlist.getIsPublic());
    if(!playlist.getIsPublic()){
      playlist.setIsCollaborative(false);
    }
    playlistDao.updatePlaylist(playlist);
    return playlist;
  }

  @Override
  public List<Playlist> getTopPlaylists() {
    List<Playlist> p = playlistDao.getTopPlaylists();
    return p;
  }
}
