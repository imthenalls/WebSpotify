
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
import com.team0n3.webspotify.service.ArtistService;
import java.util.ArrayList;
import java.util.Collection;
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
  @Autowired
  private ArtistService artistService;
  
  private List<Playlist> listOfPlaylists = new ArrayList<Playlist>();
  private List<Playlist> followedPlaylists = new ArrayList<Playlist>();

  @RequestMapping(value="/", method=RequestMethod.GET)
  public ModelAndView handleRequest(HttpSession session) {
    ModelAndView model = new ModelAndView("login");
    return model;
  }

  @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
  public ModelAndView doLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
    User user=userService.login(username, password);
    if(user==null){
        return new ModelAndView("redirect:/");
    }
    listOfPlaylists.addAll(user.getCreatedPlaylists());
    followedPlaylists.addAll(user.getFollowedPlaylists());
    session.setAttribute("currentUser", user);
    session.setAttribute("PlaylistList",listOfPlaylists);
    session.setAttribute("FollowedPlaylists", followedPlaylists);
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
    User user = userService.signup(username, password, email);
    if(user==null){
      return new ModelAndView("signup");
    }
    return new ModelAndView("redirect:/");
  }

  @RequestMapping(value = "/browse", method = RequestMethod.GET)
  public ModelAndView browse(HttpSession session) {
    if(session.getAttribute("currentUser")==null){
      return new ModelAndView("login");
    }
    return new ModelAndView("browse");
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

  @RequestMapping(value="/addToPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void addToPlaylist(@RequestParam int playlist, @RequestParam int song, HttpSession session){
    songService.AddSongToPlaylist(song, playlist);
  }
  
  @RequestMapping(value="/followPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void followPlaylist(@RequestParam int playlist, HttpSession session){
      User currentUser = (User)session.getAttribute("currentUser");
      followedPlaylists.add(playlistService.getPlaylistByID(playlist));
      userService.addPlaylistToFollow(currentUser.getUsername(), playlist);
  }
  
  @RequestMapping(value="/unfollowPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowPlaylist(@RequestParam int playlist, HttpSession session){
    boolean found=false;
    for(Playlist p:followedPlaylists){
      if(p.getPlaylistID()== playlist){
        followedPlaylists.remove(p);
        found=true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      //followedPlaylists.remove(playlistService.getPlaylistByID(playlist));
      userService.unfollowPlaylist(currentUser.getUsername(), playlist);
    }
  }
  
  @RequestMapping(value = "/viewAlbum", method= RequestMethod.GET)
  @ResponseBody
  public void viewAlbum(@RequestParam int albumID, HttpSession session){
    Album album = albumService.getAlbum(albumID);
    List<Song> albumSongs = (List<Song>) album.getSongs();
    session.setAttribute("currentAlbum",album);
    session.setAttribute("albumSongs",albumSongs);
  }    

  @RequestMapping(value = "/viewSongs", method= RequestMethod.GET)
  @ResponseBody
  public void viewSongs(HttpSession session){
    List<Song> followSongs = songService.listAllSongs();
    session.setAttribute("songList",followSongs);
  }
  
  @RequestMapping(value = "/viewFollowedAlbums", method= RequestMethod.GET)
  @ResponseBody
  public void viewFollowedAlbums(HttpSession session){
    List<Album> followAlbum = albumService.listAllAlbums();
    session.setAttribute("followAlbum",followAlbum);
  }
  
  @RequestMapping(value = "/viewAllPlaylists", method= RequestMethod.GET)
  @ResponseBody
  public void viewAllPlaylists(HttpSession session){
    List<Playlist> allPlaylists = playlistService.listAllPlaylists();
    System.out.println(allPlaylists.get(0));
    session.setAttribute("allPlaylists",allPlaylists);
  }
  
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request){
    request.getSession().invalidate();
    return "login";
  }
}
