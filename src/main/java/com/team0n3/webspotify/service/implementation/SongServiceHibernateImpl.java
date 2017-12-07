
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.SongService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SongServiceHibernateImpl implements SongService{

  @Autowired
  private SongDAO songDao;
  
  @Autowired
  private PlaylistService playlistService;
  
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public Song getSong(int songId) {
    Song song = songDao.getSong(songId);
    if(song == null)
        return null;
    return song;
  }

  @Transactional(readOnly = false)
  @Override
  public void addNewSong(String title) {
    Song song = new Song(title);
    songDao.addSong(song);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Song> listAllSongs()
  {
    List<Song> listSongs = songDao.listSongs();
    return listSongs;
  }

  @Transactional(readOnly = false)
  @Override
  public Song addSongToPlaylist(int songId, int playlistId) {
    Song song = getSong(songId);
    Playlist playlist = playlistService.getPlaylist(playlistId);
    Collection<Playlist> contains = song.getContainedInPlaylists();
    contains.add(playlist);
    song.setContainedInPlaylists(contains);
    songDao.updateSong(song);
    return song;
  }
  
  @Transactional(readOnly = false)
  @Override
  public void deleteSongFromPlaylist(int playlistId, int songId){
      
    Playlist playlist = playlistService.getPlaylist(playlistId);
    Song song = getSong(songId);
    Collection<Playlist> contains = song.getContainedInPlaylists();
    contains.remove(playlist);
    song.setContainedInPlaylists(contains);
    songDao.mergeSong(song);
    
  }
  
    @Transactional(readOnly = true)
  @Override
  public List<Song> search(String keyword, boolean limit)
  {
    List<Song> listSongs = songDao.search(keyword, limit);
    return listSongs;
  }
  @Transactional(readOnly = false)
  @Override
  public void incrementTotalPlays(int songId){
    Song song = songDao.getSong(songId);
    int totalPlays = song.getTotalPlays();
    totalPlays++;
    song.setTotalPlays(totalPlays);
    int unpayedPlays = song.getUnpayedPlays();
    unpayedPlays++;
    song.setUnpayedPlays(unpayedPlays);
    songDao.updateSong(song);
    System.out.println(song.getUnpayedPlays()+"the song is fucking retarded");
  }
 
  @Override
  @Transactional(readOnly = false)
  public void updateFollowerCount(int songId){
    Song song = songDao.getSong(songId);
    Collection<User> followers = song.getFollowers();
    song.setNumFollowers(followers.size());
    songDao.updateSong(song);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<Song> getTop50Songs(){
    /*List<Song> allSongs = songDao.listSongs();
    List<Song> top50Songs = new ArrayList();
    Song least = null;
    Song most = null;
    for(Song s : allSongs){
      if(least != null && most != null){
        if(s.getTotalPlays() > least.getTotalPlays()){
          if(top50Songs.size() == 50){//more than least but no space
            top50Songs.remove(least);
            top50Songs.add(s);
            least = most;//now need to update for a new least in top50
            for(Song s1 : top50Songs){
              if(s1.getTotalPlays() < least.getTotalPlays()){
                least = s1;
              }
            }
          }else{
             top50Songs.add(s); // more than least but still sapce for it
          }
          if(s.getTotalPlays() > most.getTotalPlays())
            most = s;//check for new most
        }else if(top50Songs.size() != 50){//less than least but still space for it
          top50Songs.add(s);
          least = s;
        }else{
          //less than least and no space for it
        }
      }else{
        least = s;
        most = s;
        top50Songs.add(s);
      }
    }
    return top50Songs;*/
    return songDao.getTop50();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<String> getGenreList(){
    return songDao.getGenreList();
  }
}
