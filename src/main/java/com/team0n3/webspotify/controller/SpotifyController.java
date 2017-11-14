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

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ListIterator;

 
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Concert;
import com.team0n3.webspotify.model.Venue;

import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.AlbumService;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.ConcertService;
import com.team0n3.webspotify.service.VenueService;

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
    private ArtistService artistService;
    
    @Autowired
    private PlaylistService playlistService;
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private AlbumService albumService;
    
    @Autowired
    private ConcertService concertService;
    
    @Autowired
    private VenueService venueService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView handleRequest(HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/login");
        return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUser(HttpSession session) {
        ModelAndView model;
        if(null == session.getAttribute("loggedIn")){
            model = new ModelAndView("login");
            return model;    
        }
        model=new ModelAndView("browse");
        return model;
    }
    
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user = userService.login(username, password);
        if(user == null){
            return new ModelAndView("redirect:/");
        }
        session.setAttribute("loggedIn", user);
        ModelAndView model= new ModelAndView("redirect:/browse");
        return model;   
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpSession session) {
        
        ModelAndView model;
        if(null == session.getAttribute("loggedIn")){
            model = new ModelAndView("signup");
            return model;    
        }
        model = new ModelAndView("browse");
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
    
    @RequestMapping(value = "/testPage", method = RequestMethod.GET)
    public ModelAndView artist(HttpSession session) {
        ModelAndView model;
        List<Artist> listOfArtists = artistService.listAllArtists();
        List<Album> listOfAlbums = albumService.listAllAlbums();
        List<Song> listOfSongs = songService.listAllSongs();
        List<Concert> listOfConcerts = concertService.listAllConcerts();
        List<Venue> listOfVenues = venueService.listAllVenues();
        
        session.setAttribute("ArtistList", listOfArtists);
        session.setAttribute("AlbumList", listOfAlbums);
        session.setAttribute("SongList", listOfSongs);
        session.setAttribute("ConcertList", listOfConcerts);
        session.setAttribute("VenueList", listOfVenues);
        
        model = new ModelAndView("testPage");
        return model;
    }

    @RequestMapping(value = "/doAddArtist", method = RequestMethod.POST)
    public ModelAndView doAddArtist(@RequestParam String artistName, HttpSession session) {
        artistService.addNewArtist(artistName);
        return new ModelAndView("testPage");
    }
    
    /*@RequestMapping(value = "/listArtists", method = RequestMethod.GET)
    public ModelAndView listArtists(HttpSession session) {
       
        return new ModelAndView("artistPage");
    }*/

    @RequestMapping(value = "/doCreatePlaylist", method = RequestMethod.POST)
    public void doCreatePlaylist(@RequestParam String playlistName, @RequestParam String imagePath, @RequestParam String description, HttpSession session){
        User currentUser = (User)session.getAttribute("currentUser");
        Playlist playlist = playlistService.createPlaylist(playlistName,imagePath,description,currentUser);
        //session.setAttribute("currentPlaylist", user);  
    }
}
