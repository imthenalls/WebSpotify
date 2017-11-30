
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceHibernateImpl implements UserService{
  
  @Autowired
  private UserDAO userDao;
  @Autowired
  private PlaylistDAO playlistDao;
  @Autowired
  private ArtistDAO artistDao;
  @Autowired
  private SongDAO songDao;
  @Autowired
  private AlbumDAO albumDao;
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public User login(String username, String password) {
    User user= userDao.getUser(username);
    if(user==null){
      return null;
    }
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return null;
    }
    md.update(user.getSalt());
    md.update(password.getBytes());
    byte hashedPass[] = md.digest();
    if(!Arrays.equals(user.getPassword(), hashedPass)){
      return null;
    }
    return user;
  }

  @Transactional(readOnly = false)
  @Override
  public User signup(String username, String password, String email) {
    SecureRandom random = new SecureRandom();
    byte salt[] = new byte[12];
    MessageDigest md = null;
    if(null!=userDao.getUser(username)){
      return null;
    }
   try{
      InternetAddress internetAddress = new InternetAddress(email);
      internetAddress.validate();
    }catch(Exception e){
      return null;
    }
    if(userDao.findByEmail(email)!=null){
      return null;
    }
    try {
        md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return null;
    }
    random.nextBytes(salt);
    md.update(salt);
    md.update(password.getBytes());
    byte hashedPass[]=md.digest();
    User user= new User(username, email, hashedPass, salt);
    userDao.addUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly=false)
  public void followPlaylist(String userId, int playlistId){
    Playlist playlist = playlistDao.getPlaylist(playlistId);
    User user = userDao.getUser(userId);
    Collection<Playlist> followed = user.getFollowedPlaylists();
    followed.add(playlist);
    user.setFollowedPlaylists(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void unfollowPlaylist(String userId, int playlistId){
    Playlist playlist = playlistDao.getPlaylist(playlistId);
    User user = userDao.getUser(userId);
    Collection<Playlist> followed = user.getFollowedPlaylists();
    followed.remove(playlist);
    user.setFollowedPlaylists(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void followArtist(String userId, int artistId){
    Artist artist = artistDao.getArtist(artistId);
    User user = userDao.getUser(userId);
    Collection<Artist> followed = user.getFollowedArtists();
    followed.add(artist);
    user.setFollowedArtists(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void unfollowArtist(String userId, int artistId){
    Artist artist = artistDao.getArtist(artistId);
    User user = userDao.getUser(userId);
    Collection<Artist> followed = user.getFollowedArtists();
    followed.remove(artist);
    user.setFollowedArtists(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void followSong(String userId, int songId){
    Song song = songDao.getSong(songId);
    User user = userDao.getUser(userId);
    Collection<Song> followed = user.getFollowedSongs();
    followed.add(song);
    user.setFollowedSongs(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void unfollowSong(String userId, int songId){
    Song song = songDao.getSong(songId);
    User user = userDao.getUser(userId);
    Collection<Song> followed = user.getFollowedSongs();
    followed.remove(song);
    user.setFollowedSongs(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false)
  public void followAlbum(String userId, int albumId){
    Album album = albumDao.getAlbum(albumId);
    User user = userDao.getUser(userId);
    Collection<Album> followed = user.getFollowedAlbums();
    followed.add(album);
    user.setFollowedAlbums(followed);
    userDao.updateUser(user);
  }
  
  @Override
  @Transactional(readOnly=false) 
  public void unfollowAlbum(String userId, int albumId){
    Album album = albumDao.getAlbum(albumId);
    User user = userDao.getUser(userId);
    Collection<Album> followed = user.getFollowedAlbums();
    followed.remove(album);
    user.setFollowedAlbums(followed);
    userDao.updateUser(user);
  }
  
  @Override
  public List<Playlist> getCreated(String username) {
    User user= userDao.getUser(username);
    Collection<Playlist> play = user.getCreatedPlaylists();
    List<Playlist> created = new ArrayList(play);
    return created;
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<User> listAllUsers(){
      List<User> listArtists = userDao.listUsers();
      return listArtists;
  }
    
  @Transactional(readOnly = true)
  @Override
  public List<User> search(String keyword){
      List<User> listUsers = userDao.search(keyword);
      return listUsers;
  }
  
  @Transactional(readOnly = false)
  @Override
  public void AddArtistAdmin(String username, String artistName,int popularity, String imagePath){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Artist artist = new Artist(artistName,popularity,imagePath);
          System.out.println(artist.toString());
          artistDao.addArtist(artist);
      }
  }
}
