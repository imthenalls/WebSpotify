/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.AlbumService;
import com.team0n3.webspotify.service.UserService;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@RequestMapping("album")
public class AlbumController {
  @Autowired
  private UserService userService;
  
  @Autowired
  private AlbumService albumService;
  
  @RequestMapping(value="/followAlbum", method=RequestMethod.POST)
  @ResponseBody
  public void followAlbum(@RequestParam int albumId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    List<Album> followedAlbums = (List<Album>)session.getAttribute("followedAlbums");
    followedAlbums.add(albumService.getAlbum(albumId));
    session.setAttribute("followedAlbums",followedAlbums);
    currentUser = userService.followAlbum(currentUser.getUsername(), albumId);
    session.setAttribute("currentUser",currentUser);
  }
  
  @RequestMapping(value="/unfollowAlbum", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowAlbum(@RequestParam int albumId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    List<Album> followedAlbums = (List<Album>)session.getAttribute("followedAlbums");
    for(Album a:followedAlbums){
      if(a.getAlbumId() == albumId){
        followedAlbums.remove(a);
        session.setAttribute("followedAlbums",followedAlbums);
        currentUser = userService.unfollowAlbum(currentUser.getUsername(), albumId);
        session.setAttribute("currentUser",currentUser);
        return;
      }
    }  
  }
  
  @RequestMapping(value = "/viewAlbum", method= RequestMethod.GET)
  @ResponseBody
  public void viewAlbum(@RequestParam int albumID, HttpSession session){
    Album album = albumService.getAlbum(albumID);
    List<Album> followedAlbums = (List<Album>)session.getAttribute("followedAlbums");
    List<Song> albumSongs = (List<Song>) album.getSongs();
    session.setAttribute("currentAlbum",album);
    session.setAttribute("albumSongs",albumSongs);
    session.setAttribute("followedAlbums",followedAlbums);
  }  
  
  @RequestMapping(value = "/viewAdminAllAlbums", method= RequestMethod.GET)
  @ResponseBody
  public void viewAdminAllAlbums( HttpSession session){
    User user = (User)session.getAttribute("currentUser");   
    if(user.getAccountType() == AccountType.Admin){
      List<Album> allAlbums = albumService.listAllAlbums();
      session.setAttribute("allAlbums",allAlbums);
    }
  } 
  
  @RequestMapping( value = "/adminAddAlbum", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddAlbum(@RequestParam String albumName,@RequestParam int popularity, @RequestParam String imagePath, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
      userService.adminAddAlbum(user.getUsername(),albumName,  popularity,  imagePath);    
  }
  
  @RequestMapping( value = "/adminRemoveAlbum", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveAlbum(@RequestParam int albumId, HttpSession session){
    List<Album> allAlbums = albumService.listAllAlbums();
    boolean found = false;
    for(Album a : allAlbums){
      if(a.getAlbumId() == albumId){
        allAlbums.remove(a);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemoveAlbum(currentUser.getUsername(), albumId);
      session.setAttribute("allAlbums",allAlbums);
    }
  }
  
  @RequestMapping(value = "/viewAdminAllAlbums", method= RequestMethod.GET)
  @ResponseBody
  public void viewAdminAllAlbums(HttpSession session){
    /** CURRENTLY VIEWS ALL Albums **/
    List<Album> allAlbums = albumService.listAllAlbums();
    session.setAttribute("allAlbums",allAlbums);
  }
}
