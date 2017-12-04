/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.UserService;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author spike
 */
@Controller
@RequestMapping("playlist")
public class PlaylistController {
  @Autowired
  private PlaylistService playlistService;
  
  @Autowired
  private SongService songService;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private ServletContext context;
  
  @RequestMapping(value = "/createPlaylist", method = RequestMethod.POST)
  @ResponseBody
  public void createPlaylist(@RequestParam String playlistName, @RequestParam String imagePath, @RequestParam String description, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    Playlist playlist = playlistService.createPlaylist(playlistName,imagePath,description,currentUser);
    /**
    String relativePath = "/resources/image/playlist";
    String absolutePath = context.getRealPath(relativePath);
    File uploadedFile = new File(absolutePath,imagePath);
    **/
    List<Playlist> createdPlaylists = (List<Playlist>)session.getAttribute("createdPlaylists");
    createdPlaylists.add(playlist);
    session.setAttribute("createdPlaylists", createdPlaylists);  
  }

  @RequestMapping(value = "/viewPlaylist", method= RequestMethod.GET)
  @ResponseBody
  public void viewPlaylist(@RequestParam int playlistID, HttpSession session){
    Playlist playlist = playlistService.getPlaylist(playlistID);
    List<Song> playlistSongs = playlistService.getSongsInPlaylists(playlistID);
    session.setAttribute("currentPlaylist",playlist);
    session.setAttribute("songList",playlistSongs);
  }
  
  @RequestMapping(value = "/renamePlaylist", method= RequestMethod.POST)
  @ResponseBody
  public void renamePlaylist(@RequestParam String playlistName, HttpSession session){ 
      System.out.println("HELLO");
    Playlist playlist = (Playlist)session.getAttribute("currentPlaylist");
    playlistService.renamePlaylist(playlist.getPlaylistID(),playlistName);
  }
  
  @RequestMapping(value="/deletePlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void deletePlaylist(HttpSession session){
    Playlist playlist = (Playlist)session.getAttribute("currentPlaylist");
    List<Playlist> createdPlaylists = (List<Playlist>)session.getAttribute("createdPlaylists");
    for(Playlist p:createdPlaylists){
      if(p.getPlaylistID() == playlist.getPlaylistID()){
        createdPlaylists.remove(p);
        break;
      }
    }
    playlistService.deletePlaylist(playlist);
    session.setAttribute("createdPlaylists",createdPlaylists);
  }

  @RequestMapping(value="/addSongToPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void addSongToPlaylist(@RequestParam int playlist, @RequestParam int song, HttpSession session){
    songService.addSongToPlaylist(song, playlist);
    playlistService.updateSongCount(playlist);
  }
  
  @RequestMapping(value="/deleteSongFromPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void deleteSongFromPlaylist(@RequestParam int playlistId, @RequestParam int songId, HttpSession session){
    List<Song> playlistSongs = (List<Song>)session.getAttribute("songList");
    for(Song s:playlistSongs){
      if(s.getSongId() == songId){
        playlistSongs.remove(s);
        break;
      }
    }
    songService.deleteSongFromPlaylist(playlistId,songId);
    playlistService.updateSongCount(playlistId);
    session.setAttribute("songList",playlistSongs);
  }
  
  @RequestMapping(value="/followPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void followPlaylist(@RequestParam int playlist, HttpSession session){
      User currentUser = (User)session.getAttribute("currentUser");
      List<Playlist> followedPlaylists = (List<Playlist>)session.getAttribute("followedPlaylists");
      followedPlaylists.add(playlistService.getPlaylist(playlist));
      userService.followPlaylist(currentUser.getUsername(), playlist);
      playlistService.updateFollowerCount(playlist);
  }

  @RequestMapping(value="/unfollowPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowPlaylist(@RequestParam int playlist, HttpSession session){
    boolean found=false;
    List<Playlist> followedPlaylists = (List<Playlist>)session.getAttribute("followedPlaylists");
    for(Playlist p:followedPlaylists){
      if(p.getPlaylistID( ) == playlist){
        followedPlaylists.remove(p);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.unfollowPlaylist(currentUser.getUsername(), playlist);
      playlistService.updateFollowerCount(playlist);
    }
  }
  
  @RequestMapping(value = "/viewAllPlaylists", method= RequestMethod.GET)
  @ResponseBody
  public void viewAllPlaylists(HttpSession session){
    List<Playlist> allPlaylists = playlistService.listAllPlaylists();
    System.out.println(allPlaylists.get(0));
    session.setAttribute("allPlaylists",allPlaylists);
  }
  
  @RequestMapping( value = "/adminAddPlaylist", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddPlaylist(@RequestParam String playlistName,@RequestParam String imagePath, @RequestParam String description, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        System.out.println(user.toString());
        userService.adminAddPlaylist(user.getUsername(), playlistName,imagePath, description);    
    }
  }
  
  @RequestMapping( value = "/adminRemovePlaylist", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemovePlaylist(@RequestParam int playlistId, HttpSession session){
    List<Playlist> allPlaylists = playlistService.listAllPlaylists();
    boolean found = false;
    for(Playlist p : allPlaylists){
      if(p.getPlaylistID() == playlistId){
        allPlaylists.remove(p);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemovePlaylist(currentUser.getUsername(), playlistId);
      session.setAttribute("allPlaylists",allPlaylists);
    }
  }
}
