/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.SongPlayer;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.PlaylistService;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author spike
 */
@Controller
@RequestMapping("/songPlayer")
public class SongPlayerController {
  
  @Autowired
  private SongService songService;
  
  @Autowired
  private PlaylistService playlistService;
  
  private SongPlayer player = new SongPlayer();
  
  @RequestMapping(value="/playSong",method=RequestMethod.GET)
  @ResponseBody
  public void playSong(@RequestParam int songId, @RequestParam String setType, @RequestParam int songIndex, HttpSession session){
    Song song = songService.getSong(songId);
    if(setType.equals("album")){
      Album currentAlbum = (Album)session.getAttribute("currentAlbum");
      Collection<Song> albumSongs = currentAlbum.getSongs();
      player.setQueues(albumSongs,songIndex);
    }
    else if(setType.equals("playlist")){
      Playlist currentPlaylist = (Playlist)session.getAttribute("currentPlaylist");
      Collection<Song> playlistSongs = currentPlaylist.getSongs();
      player.setQueues(playlistSongs,songIndex);
    }
    else if(setType.equals("artist")){
      Collection<Song> popularSongs = (Collection<Song>)session.getAttribute("popularSongs");
      player.setQueues(popularSongs,songIndex);
    }
    else if (setType.equals("followedSongs")){
      Collection<Song> followedSongs = (Collection<Song>)session.getAttribute("followedSongs");
      player.setQueues(followedSongs,songIndex);
    }
    else if(setType.equals("topSongs")){
      Collection<Song> topSongs = (Collection<Song>)session.getAttribute("topSongs");
      player.setQueues(topSongs,songIndex);
    }
    else if (setType.equals("queue")){
      Collection<Song> queueSongs = (Collection<Song>)session.getAttribute("queueSongs");
      player.setQueues(queueSongs,songIndex);
    }
    else if (setType.equals("history")){
      Collection<Song> historySongs = (Collection<Song>)session.getAttribute("historySongs");
      player.setQueues(historySongs,songIndex);
    }
    songService.incrementTotalPlays(song.getSongId());

    session.setAttribute("currentSong",song);
  }

  @RequestMapping(value="/playNext",method=RequestMethod.GET)
  @ResponseBody
  public void playNext(HttpSession session){
    Song nextSong = player.getNextSong();
    session.setAttribute("currentSong",nextSong);
  }
  
  @RequestMapping(value="/playPrev",method=RequestMethod.GET)
  @ResponseBody
  public void playPrev(HttpSession session){
    Song prevSong = player.getPrevSong();
    session.setAttribute("currentSong",prevSong);
  }
  
  @RequestMapping( value = "/toggleRepeat", method = RequestMethod.GET)
  @ResponseBody
  public void toggleRepeat(@RequestParam String setting, HttpSession session){
    player.toggleRepeat(setting);
  }
  
  @RequestMapping(value="/toggleShuffle",method=RequestMethod.GET)
  @ResponseBody
  public void toggleShuffle(HttpSession session){
    player.toggleShuffle();
  }
  
  @RequestMapping( value = "/getLyrics", method = RequestMethod.GET)
  @ResponseBody
  public String getLyrics(@RequestParam String artistName, @RequestParam String songName, HttpSession session) throws IOException{
      String baseUrl = "http://lyrics.wikia.com/wiki/";
      artistName = artistName.replace(' ', '_');
      songName = songName.replace(' ', '_'); 
      String url = baseUrl + artistName + ":"+ songName;
      Document page = Jsoup.connect(url).timeout(6000).get();
      Element lyrics = page.select("div.lyricbox").first();
      return lyrics.toString();
  }
  
  @RequestMapping(value="/viewQueue",method=RequestMethod.GET)
  @ResponseBody
  public void viewQueue(HttpSession session){
    List<Song> queueSongs = player.getCorrectQueue();
    session.setAttribute("queueSongs",queueSongs);
  }
  
  @RequestMapping(value="/viewHistory",method=RequestMethod.GET)
  @ResponseBody
  public void viewHistory(HttpSession session){
    List<Song> historySongs = player.getHistory();
    session.setAttribute("historySongs",historySongs);
  }
  
  @RequestMapping(value="/addSongToQueue",method=RequestMethod.GET)
  @ResponseBody
  public void addSongToQueue(@RequestParam int songId, HttpSession session){
    Song song = songService.getSong(songId);
    List<Song> queueSongs = player.addSongToQueue(song);
    session.setAttribute("queueSongs",queueSongs);
  }
  
  @RequestMapping(value="/addPlaylistToQueue",method=RequestMethod.GET)
  @ResponseBody
  public void addPlaylistToQueue(@RequestParam int playlistId, HttpSession session){
    Playlist playlist = playlistService.getPlaylist(playlistId);
    List<Song> playlistSongs = (List<Song>)playlist.getSongs();
    List<Song> queueSongs = player.addPlaylistToQueue(playlistSongs);
    session.setAttribute("queueSongs",queueSongs);
  }
}
