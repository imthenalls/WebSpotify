
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
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  private SongService songService;
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${user.defaultPath}")
  String defaultPath;
  
  @Override
  public User login(String username, String password) {
    User user = userDao.getUser(username);
    if(user == null){
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
  public String signup(String username, String password, String email, boolean isArtist) {
    
    SecureRandom random = new SecureRandom();
    byte salt[] = new byte[12];
    MessageDigest md = null;
    if(null != userDao.getUser(username)){  //account already exists with that email
      return "duplicate";
    }
   try{
      InternetAddress internetAddress = new InternetAddress(email);
      internetAddress.validate();
    }catch(Exception e){ //invalid email
      return "invalidEmail";
    }
    if(userDao.findByEmail(email)!=null){ //account already exists with that email
      return "duplicate";
    }
    try {
        md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return "hashing";
    }
    random.nextBytes(salt);
    md.update(salt);
    md.update(password.getBytes());
    byte hashedPass[] = md.digest();
    User user = new User(username, email, hashedPass, salt);   
    user.setImagePath(defaultPath);
    if(isArtist){
      user.setAccountType(AccountType.UnapprovedArtist);
    }
    userDao.addUser(user);
    return "noError";
  }
  @Transactional(readOnly = false)
  @Override
  public void addUser(String username, String password, String email, boolean isArtist) {
    SecureRandom random = new SecureRandom();
    byte salt[] = new byte[12];
    MessageDigest md = null;
    try {
          md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
        }
    random.nextBytes(salt);
    md.update(salt);
    md.update(password.getBytes());
    byte hashedPass[] = md.digest();
    User user = new User(username, email, hashedPass, salt);   
    user.setImagePath(defaultPath);
    if(isArtist){
      user.setAccountType(AccountType.UnapprovedArtist);
    }
    userDao.addUser(user);
  }
  
  @Override
  @Transactional(readOnly = true)
   public User getUser(String username){
       User user = userDao.getUser(username);
       return user;
   }
  
  @Override
  @Transactional(readOnly = false)
  public User followPlaylist(String userId, int playlistId){
    Playlist playlist = playlistDao.getPlaylist(playlistId);
    User user = userDao.getUser(userId);
    Collection<Playlist> followed = user.getFollowedPlaylists();
    followed.add(playlist);
    user.setFollowedPlaylists(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User unfollowPlaylist(String userId, int playlistId){
    Playlist playlist = playlistDao.getPlaylist(playlistId);
    User user = userDao.getUser(userId);
    Collection<Playlist> followed = user.getFollowedPlaylists();
    followed.remove(playlist);
    user.setFollowedPlaylists(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User followArtist(String userId, int artistId){
    Artist artist = artistDao.getArtist(artistId);
    User user = userDao.getUser(userId);
    Collection<Artist> followed = user.getFollowedArtists();
    followed.add(artist);
    user.setFollowedArtists(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User unfollowArtist(String userId, int artistId){
    Artist artist = artistDao.getArtist(artistId);
    User user = userDao.getUser(userId);
    Collection<Artist> followed = user.getFollowedArtists();
    followed.remove(artist);
    user.setFollowedArtists(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User followSong(String userId, int songId){
    Song song = songDao.getSong(songId);
    User user = userDao.getUser(userId);
    Collection<Song> followed = user.getFollowedSongs();
    followed.add(song);
    user.setFollowedSongs(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User unfollowSong(String userId, int songId){
    Song song = songDao.getSong(songId);
    User user = userDao.getUser(userId);
    Collection<Song> followed = user.getFollowedSongs();
    followed.remove(song);
    user.setFollowedSongs(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User followAlbum(String userId, int albumId){
    Album album = albumDao.getAlbum(albumId);
    User user = userDao.getUser(userId);
    Collection<Album> followed = user.getFollowedAlbums();
    followed.add(album);
    user.setFollowedAlbums(followed);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false) 
  public User unfollowAlbum(String userId, int albumId){
    Album album = albumDao.getAlbum(albumId);
    User user = userDao.getUser(userId);
    Collection<Album> followed = user.getFollowedAlbums();
    followed.remove(album);
    user.setFollowedAlbums(followed);
    userDao.updateUser(user);
    return user;
  }
  @Transactional(readOnly = true)
  @Override
  public List<Playlist> getCreatedPlaylists(String username) {
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
  public List<User> search(String keyword, boolean limit){
      List<User> listUsers = userDao.search(keyword, limit);
      return listUsers;
  }

  @Transactional(readOnly = false)
  @Override
  public void adminAddSong(String title){
    Song song = new Song(title);
    System.out.println(song.toString());
    songDao.addSong(song);
  }

  @Transactional(readOnly = false)
  @Override
  public void adminAddAlbum(String albumName, int popularity, String imagePath ){

     Album album = new Album(albumName,popularity,imagePath);
     System.out.println(album.toString());
     albumDao.addAlbum(album);
  }

  @Transactional(readOnly = false)
  @Override
  public void adminEditArtistBio(int artistId){
  }

  @Transactional(readOnly = false)
  @Override
  public void adminApproveFreeUser(String approve){
    User userToApprove = userDao.getUser(approve);
    userToApprove.setAccountType(AccountType.Free);
    userDao.updateUser(userToApprove);
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminApproveArtistUser(String approve){
    User userToApprove = userDao.getUser(approve);
    if(userToApprove.getAccountType() == AccountType.UnapprovedArtist){
      userToApprove.setAccountType(AccountType.Artist);
      userDao.updateUser(userToApprove);
      Artist artist = new Artist(userToApprove.getUsername(), userToApprove);
      artistDao.addArtist(artist);
      //fix this 
      }
  }

  @Transactional(readOnly = false)
  @Override
  public void adminDeleteUser(User u){
    userDao.deleteUser(u);
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminDeleteArtist(Artist a){
    artistDao.deleteArtist(a);
  }

  @Transactional(readOnly = false)
  @Override
  public void adminDeleteAlbum(Album a){
    albumDao.deleteAlbum(a);
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminDeleteSong( Song s){
    songDao.deleteSong(s);
  } 
  
  @Transactional(readOnly = false)
  @Override
  public void adminDeletePlaylist(Playlist p){
    playlistDao.deletePlaylist(p);
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminSendRoyaltyChecks(String artistId){
    
  }

  @Transactional(readOnly = false)
  @Override
  public void changeProfilePic(String username, String path) {
    User user= userDao.getUser(username);
    user.setImagePath(path);
    userDao.updateUser(user);
  }

  @Override
  public void adminAddArtist(String username, String artistName, int popularity, String imagePath) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminRemoveArtist(String username, int artistId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminAddPlaylist(String username, String playlistName, String imagePath, String description) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminRemovePlaylist(String username, int playlistId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminAddSong(String username, String title) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminRemoveSong(String username, int songId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminEditSong(String username, int songId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminAddAlbum(String username, String albumName, int popularity, String imagePath) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminRemoveAlbum(String username, int albumId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminEditArtistBio(String username, int artistId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void artistCheckSongMetrics(String username, int artistId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void artistCheckRoyalties(String username, int artistId) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminApproveFreeUser(String username, String approve) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminApproveArtistUser(String username, String approve) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void adminRemoveUser(String admin, String removeUser) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  @Transactional(readOnly = false)
  public boolean removeUser(String username, String password) {
    User user= userDao.getUser(username);
    if(user == null){
      return false;
    }
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException ex) {
      return false;
    }
    md.update(user.getSalt());
    md.update(password.getBytes());
    byte hashedPass[] = md.digest();
    if(!Arrays.equals(user.getPassword(), hashedPass)){
      return false;
    }
    userDao.deleteUser(user);
    return true;
  }
  
  @Override
  @Transactional(readOnly = true)
  public Collection<User> viewPlaylistFollowers(int playlistId){
    Playlist p = playlistDao.getPlaylist(playlistId);
    return p.getFollowers();
  }
  /* put this in a controller to test following a playlist and viewing it
    Playlist play = playlistService.getPlaylist(42);
    session.setAttribute("testPlaylist",play);
    Collection<User> allUsers = userService.listAllUsers();
    session.setAttribute("allUsersTest", allUsers);
    Collection<User> allUsersTest = (ArrayList)session.getAttribute("allUsersTest");
    System.out.println("ADDING FOLLOWERS NOW ");
    for(User u : allUsersTest){
      userService.followPlaylist(u.getUsername(),42);
      System.out.println("add test followers "+u.toString());
    }
    session.setAttribute("allUsersTest", allUsersTest);
  */

  @Override
  @Transactional(readOnly = false)
  public void banUser(String username){
    User user = userDao.getUser(username); 
    if(user.getAccountType() == AccountType.Banned){
      user.setAccountType(AccountType.Free);
      userDao.updateUser(user);
    }
    else{
      user.setAccountType(AccountType.Banned);
      userDao.updateUser(user);
    }
  }
}
