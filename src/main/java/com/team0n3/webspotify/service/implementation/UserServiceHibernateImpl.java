
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
  public String signup(String username, String password, String email) {
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
    userDao.addUser(user);
    return "noError";
  }
  @Override
  @Transactional(readOnly=true)
   public User getUser(String username){
       User user = userDao.getUser(username);
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
  public List<User> search(String keyword){
      List<User> listUsers = userDao.search(keyword);
      return listUsers;
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminAddArtist(String username, String artistName,int popularity, String imagePath){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Artist artist = new Artist(artistName,popularity,imagePath);
          System.out.println(artist.toString());
          artistDao.addArtist(artist);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminRemoveArtist(String username, int artistId){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Artist artist = artistDao.getArtist(artistId);
          List<Song> allSongs = songDao.listSongs();
          Lock lock = new ReentrantLock();
          Lock lock2 = new ReentrantLock();
          Lock lock3 = new ReentrantLock();
          
          lock.lock();
          try {
              
              for(Song s:allSongs){
                  if((s.getArtistId().getArtistId() == artist.getArtistId())){
                      Collection<Playlist> contains = s.getContainedInPlaylists();
                      contains.removeAll(contains);
                      s.setContainedInPlaylists(contains);
                      songDao.mergeSong(s);
                      break;
                  }
              }
          } finally {
              lock.unlock();
          }
          lock2.lock();
          try {
              allSongs = songDao.listSongs();
              for(Song s:allSongs){
                  if(s.getArtistId().getArtistId() == artist.getArtistId()){
                      
                      Collection<Song> albumSongs = s.getAlbumId().getSongs();
                      albumSongs.remove(s);
                      s.getAlbumId().setSongs(albumSongs);
                      albumDao.updateAlbum(s.getAlbumId());
                      
                      Collection<Song> artistSongs = s.getArtistId().getSongs();
                      artistSongs.remove(s);
                      s.getArtistId().setSongs(artistSongs);
                      artistDao.updateArtist(s.getArtistId());
                      
                      Collection<Song> userSongs = userDao.getUser(username).getFollowedSongs();
                      userSongs.remove(s);
                      userDao.getUser(username).setFollowedSongs(userSongs);
                      userDao.updateUser(userDao.getUser(username));
                      songDao.deleteSong(s);
                      break;
                  }
              }
          } finally {
              lock2.unlock();
          }
          lock3.lock();
          try {  
              List<Album> allAlbums = albumDao.listAlbums();
              for(Album a:allAlbums){
                  if(a.getArtistId().getArtistId() == artist.getArtistId()){
                      Collection<Album> userAlbums = userDao.getUser(username).getFollowedAlbums();
                      userAlbums.remove(a);
                      userDao.getUser(username).setFollowedAlbums(userAlbums);
                      userDao.updateUser(userDao.getUser(username));
                      
                      Collection<Album> artistAlbums = artist.getAlbums();
                      artistAlbums.remove(a);
                      artist.setAlbums(artistAlbums);
                      artistDao.updateArtist(artist);
                      
                      albumDao.deleteAlbum(a);
                      break;
                  }
              }
              
          } finally {
              lock3.unlock();
          }
          Collection<Artist> userArtists = userDao.getUser(username).getFollowedArtists();
          userArtists.remove(artist);
          userDao.getUser(username).setFollowedArtists(userArtists);
          userDao.updateUser(userDao.getUser(username));
          artistDao.deleteArtist(artist);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminAddPlaylist( String username, String playlistName,String imagePath, String description){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Playlist playlist = new Playlist(playlistName, imagePath, description, user);
          System.out.println(playlist.toString());
          playlistDao.addPlaylist(playlist);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminRemovePlaylist(String username, int playlistId){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Lock lock = new ReentrantLock();
          Collection<Playlist> allFollowedPlaylists = user.getFollowedPlaylists();
          lock.lock();
          try {
              for(Playlist p : allFollowedPlaylists){
                  if((p.getPlaylistID() == playlistId)){
                      allFollowedPlaylists.remove(p);
                      user.setFollowedPlaylists(allFollowedPlaylists);
                      userDao.updateUser(user);
                      break;
                  }
              }
          } finally {
              lock.unlock();
          }
          
          Lock lock2 = new ReentrantLock();
          Collection<Playlist> allCollabPlaylists = user.getCollabPlaylists();
          lock2.lock();
          try {
              for(Playlist p : allCollabPlaylists){
                  if((p.getPlaylistID() == playlistId)){
                      allCollabPlaylists.remove(p);
                      user.setCollabPlaylists(allCollabPlaylists);
                      userDao.updateUser(user);
                      break;
                  }
              }
          } finally {
              lock2.unlock();
          }
          
          Lock lock3 = new ReentrantLock();
          List<Song> allSongs = songDao.listSongs();
          lock3.lock();
          try {
              for(Song s: allSongs){
                  Collection<Playlist> inSongContained = s.getContainedInPlaylists();
                  for(Playlist p : inSongContained){
                      if((p.getPlaylistID() == playlistId)){
                          inSongContained.remove(p);
                          s.setContainedInPlaylists(inSongContained);
                          songDao.updateSong(s);
                          break;
                      }
                  }
              }
          } finally {
              lock3.unlock();
          }
          
          Playlist playlist = playlistDao.getPlaylist(playlistId);
          System.out.println(playlist.toString());
          playlistDao.deletePlaylist(playlist);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminAddSong( String username, String title ){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Song song = new Song(title);
          System.out.println(song.toString());
          songDao.addSong(song);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminRemoveSong(String username, int songId){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Lock lock = new ReentrantLock();
          Lock lock1 = new ReentrantLock();
          Lock lock2 = new ReentrantLock();
          Lock lock3 = new ReentrantLock();
          Song song = songDao.getSong(songId);
          System.out.println(song.toString());   
          
          List<User> allUsers = userDao.listUsers();
          lock.lock();
          try {
            for(User u: allUsers){
              Collection<Song> followedSongsInUser = u.getFollowedSongs();
              for(Song s:followedSongsInUser){
                if(s.getSongId() == songId){
                  followedSongsInUser.remove(s);
                  u.setFollowedSongs(followedSongsInUser);
                  userDao.updateUser(u);
                  break;
                }
              }
            }
          }finally {
              lock.unlock();
          }
          
          List<Playlist> allPlaylists = playlistDao.listPlaylists();
          lock1.lock();
          try{
            for(Playlist p:allPlaylists){
                Collection<Song> songsInP = p.getSongs();
                for(Song s1:songsInP)
                {
                  if(s1.getSongId() == songId){
                    songsInP = p.getSongs();
                    songsInP.remove(s1);
                    p.setSongs(songsInP);
                    playlistDao.updatePlaylist(p);
                    break;
                  }
                }
            }
          }finally{
              lock1.unlock();
          }
          
          List<Artist> allArtists = artistDao.listArtists();
          lock2.lock();
          try{
            for(Artist a:allArtists){
                Collection<Song> songsInA = a.getSongs();
                for(Song s2:songsInA)
                {
                  if(s2.getSongId() == songId){
                    songsInA = a.getSongs();
                    songsInA.remove(s2);
                    a.setSongs(songsInA);
                    artistDao.updateArtist(a);
                    break;
                  }
                }
            }
          }finally{
              lock2.unlock();
          }
          
          List<Album> allAlbums = albumDao.listAlbums();
          lock3.lock();
          try{
            for(Album al:allAlbums){
                Collection<Song> songsInAl = al.getSongs();
                for(Song s3:songsInAl)
                {
                  if(s3.getSongId() == songId){
                    songsInAl = al.getSongs();
                    songsInAl.remove(s3);
                    al.setSongs(songsInAl);
                    albumDao.updateAlbum(al);
                    break;
                  }
                }
            }
          }finally{
              lock3.unlock();
          }
          songDao.deleteSong(song);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminEditSong(String username, int songId){
     
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminAddAlbum( String username, String albumName, int popularity, String imagePath ){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Album album = new Album(albumName,popularity,imagePath);
          System.out.println(album.toString());
          albumDao.addAlbum(album);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminRemoveAlbum(String username, int albumId){
      User user = userDao.getUser(username);
      if(user.getAccountType() == AccountType.Admin)
      {
          Album album = albumDao.getAlbum(albumId);
          System.out.println(album.toString());
          Lock lock = new ReentrantLock();
          Lock lock1 = new ReentrantLock();
          List<User> allUsers = userDao.listUsers();
          List<Artist> allArtists = artistDao.listArtists();
          
          lock.lock();
          try {
            for(User u: allUsers){
              Collection<Album> followedAlbumsInUser = u.getFollowedAlbums();
              for(Album a:followedAlbumsInUser){
                if(a.getAlbumId() == albumId){
                  followedAlbumsInUser.remove(a);
                  u.setFollowedAlbums(followedAlbumsInUser);
                  userDao.updateUser(u);
                  break;
                }
              }
            }
          }finally {
              lock.unlock();
          }          
          lock1.lock();
          try {
            for(Artist art: allArtists){
              Collection<Album> followedAlbumsInArtist = art.getAlbums();
              for(Album a1:followedAlbumsInArtist){
                if(a1.getAlbumId() == albumId){
                  followedAlbumsInArtist.remove(a1);
                  art.setAlbums(followedAlbumsInArtist);
                  artistDao.updateArtist(art);
                  break;
                }
              }
            }
          }finally {
              lock1.unlock();
          }
          albumDao.deleteAlbum(album);
      }
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminEditArtistBio(String username, int artistId){
      
  }
  
  @Transactional(readOnly = false)
  @Override
  public void artistCheckSongMetrics(String username, int artistId){
      
  }
  
  @Transactional(readOnly = false)
  @Override
  public void artistCheckRoyalties(String username, int artistId){
      
  }
  
  @Transactional(readOnly = false)
  @Override
  public void adminApproveFreeUser(String username,String approve){
    User user = userDao.getUser(username);
    if(user.getAccountType() == AccountType.Admin)
    {
      User userToApprove = userDao.getUser(approve);
      userToApprove.setAccountType(AccountType.Free);
      userDao.updateUser(userToApprove);
    }
  }
}
