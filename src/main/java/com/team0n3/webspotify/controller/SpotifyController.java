/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

/**
 *
 * @author JSCHA
 */

import javax.servlet.http.HttpSession;
 
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;

import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.AlbumService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 
/**
 * Handles requests for the application home page.
 */
@Controller
public class SpotifyController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongService songService;
    @Autowired
    private AlbumService albumService;
    private List<Playlist> listOfPlaylists = new ArrayList<Playlist>();
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView handleRequest(HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/login");
        return model;
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUser(HttpSession session) {
        ModelAndView model;
        if(null == session.getAttribute("currentUser")){
            model = new ModelAndView("login");
            return model;    
        }
        model=new ModelAndView("browse");
        return model;
    }
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user=userService.login(username, password);
        if(user==null){
            return new ModelAndView("redirect:/");
        }
        listOfPlaylists = playlistService.listAllPlaylists();
        for(Iterator<Playlist> iterator = listOfPlaylists.iterator(); iterator.hasNext();){
            Playlist p = iterator.next();
            String listElem = p.getCreator().getUsername();
            if(!listElem.equals(user.getUsername())){
                iterator.remove();
            }
        }
        session.setAttribute("currentUser", user);
        session.setAttribute("PlaylistList",listOfPlaylists);
        ModelAndView model= new ModelAndView("redirect:/browse");
        return model;   
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpSession session) {
        
        ModelAndView model;
        if(null == session.getAttribute("currentUser")){
            model = new ModelAndView("signup");
            return model;    
        }
        model=new ModelAndView("browse");
        return model;
    }
    @RequestMapping(value = "/doSignup", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.signup(username, password, email);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    public ModelAndView browse(HttpSession session) {
        ModelAndView model = new ModelAndView("browse");
        return model;
    }
    @RequestMapping(value = "/makePlaylist", method = RequestMethod.POST)
    @ResponseBody
    public void doCreatePlaylist(@RequestParam String playlistName, @RequestParam String imagePath, @RequestParam String description, HttpSession session){
        User currentUser = (User)session.getAttribute("currentUser");
        Playlist playlist = playlistService.createPlaylist(playlistName,imagePath,description,currentUser);
        listOfPlaylists.add(playlist);
        session.setAttribute("PlaylistList", listOfPlaylists);  
    }
    @RequestMapping(value = "/viewPlaylist", method= RequestMethod.GET)
    @ResponseBody
    public void viewPlaylist(@RequestParam int playlistID, HttpSession session){
        Playlist playlist = playlistService.getPlaylistByID(playlistID);
        List<Song> playlistSongs = playlistService.getSongsInPlaylists(playlistID);
        session.setAttribute("currentPlaylist",playlist);
        session.setAttribute("songList",playlistSongs);
    }
    @RequestMapping(value="/deletePlaylist", method=RequestMethod.POST)
    @ResponseBody
    public void deletePlaylist(HttpSession session){
        Playlist playlist = (Playlist)session.getAttribute("currentPlaylist");
        //Playlist playlist = playlistService.getPlaylistByID(playlistID);
        System.out.println("before: " + listOfPlaylists.size());
        for(Playlist p:listOfPlaylists){
            if(p.getPlaylistID()== playlist.getPlaylistID()){
                listOfPlaylists.remove(p);
                break;
            }
        }
        System.out.println("after: " + listOfPlaylists.size());
        playlistService.deletePlaylist(playlist);
        session.setAttribute("PlaylistList",listOfPlaylists);
    }
    @RequestMapping(value = "/viewAlbum", method= RequestMethod.GET)
    @ResponseBody
    public void viewAlbum(@RequestParam int albumID, HttpSession session){
        Album album = albumService.getAlbum(albumID);
        List<Song> albumSongs = albumService.getAllSongsInAlbum(albumID);
        session.setAttribute("currentAlbum",album);
        session.setAttribute("albumSongs",albumSongs);
    }    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
}
