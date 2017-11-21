
package com.team0n3.webspotify.controller;

import javax.servlet.http.HttpSession;
 
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.AlbumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collection;
 
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
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView handleRequest(HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/login");
        List<Playlist> listOfPlaylists = playlistService.listAllPlaylists();
        session.setAttribute("PlaylistList",listOfPlaylists);
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
        session.setAttribute("currentUser", user);
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
    
    @RequestMapping(value = "/testPage", method = RequestMethod.GET)
    public ModelAndView artist(HttpSession session) {
        ModelAndView model;
        List<Song> listOfSongs = songService.listAllSongs();
        List<Song> albumSongs = albumService.getAllSongsInAlbum(1);
        //List<Playlist> playlists = playlistService.listAllPlaylists();
        List<Song> playlistSongs = playlistService.getSongsInPlaylists(1);
        System.out.println("helklko");
        session.setAttribute("SongList", listOfSongs);
        session.setAttribute("albumSongs",albumSongs );
        session.setAttribute("playlistSongs",playlistSongs);
        model = new ModelAndView("testPage");
        return model;
    }
    
    @RequestMapping(value = "/doAddSongToPlaylist", method = RequestMethod.POST)
    @ResponseBody
    public void doAddSongToPlaylist(@RequestParam int songId,@RequestParam int playlistId, HttpSession session) {
      //  playlistService.AddSongToPlaylist(songId, playlistId);
        songService.AddPlaylistToSong(songId, playlistId);
    }
    
    @RequestMapping(value = "/makePlaylist", method = RequestMethod.POST)
    @ResponseBody
    public void doCreatePlaylist(@RequestParam String playlistName, @RequestParam String imagePath, @RequestParam String description, HttpSession session){
        User currentUser = (User)session.getAttribute("currentUser");
        Playlist playlist = playlistService.createPlaylist(playlistName,imagePath,description,currentUser);
        //session.setAttribute("currentPlaylist", user);  
    }
    
    @RequestMapping(value = "/viewPlaylist", method= RequestMethod.GET)
    @ResponseBody
    public void viewPlaylist(@RequestParam int playlistID, HttpSession session){
        Playlist playlist = playlistService.getPlaylistByID(playlistID);
        session.setAttribute("currentPlaylist",playlist);
    }
}
