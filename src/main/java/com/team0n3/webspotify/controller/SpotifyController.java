
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import javax.servlet.http.HttpSession;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.SongPlayer;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.AlbumService;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.PaymentInfoService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
  @Autowired
  private PaymentInfoService paymentInfoService;
  private List<Playlist> listOfPlaylists = new ArrayList<Playlist>();
  private List<Playlist> followedPlaylists = new ArrayList<Playlist>();
  private SongPlayer songPlayer;
  //need a play method here and next/prev method
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
   // playlistService.renamePlaylist(playlist.getPlaylistID(),"kevin");
   //  session.setAttribute("currentPlaylist",playlist);
    // songPlayer.getNextSong();
    //songPlayer.getPrevSong();
  }
  
  @RequestMapping(value = "/renamePlaylist", method= RequestMethod.POST)
  @ResponseBody
  public void renamePlaylist(@RequestParam String playlistName, HttpSession session){ 
      System.out.println("HELLO");
    Playlist playlist = (Playlist)session.getAttribute("currentPlaylist");
    playlistService.renamePlaylist(playlist.getPlaylistID(),playlistName);
   
  }
  
  /*
  @RequestMapping(value = "/playPlaylist", method= RequestMethod.GET)
  @ResponseBody
  public void playPlaylist(@RequestParam int playlistID, HttpSession session){
    Playlist playlist = playlistService.getPlaylistByID(playlistID);
    List<Song> playlistSongs = playlistService.getSongsInPlaylists(playlistID);
    int firstSong = playlistSongs.get(0).getSongId();
    session.setAttribute("currentPlayedSong",currentPlayedSong);
    songPlayer = new SongPlayer(playlist,firstSong);
    songPlayer.play();
  }
  */
  //how do we know what page we are on?
  //need 
  @RequestMapping(value="/deletePlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void deletePlaylist(HttpSession session){
    Playlist playlist = (Playlist)session.getAttribute("currentPlaylist");
    for(Playlist p:listOfPlaylists){
      if(p.getPlaylistID()== playlist.getPlaylistID()){
        listOfPlaylists.remove(p);
        break;
      }
    }
    playlistService.deletePlaylist(playlist);
    session.setAttribute("PlaylistList",listOfPlaylists);
  }

  @RequestMapping(value="/addToPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void addToPlaylist(@RequestParam int playlist, @RequestParam int song, HttpSession session){
    songService.AddSongToPlaylist(song, playlist);
  }
  
  @RequestMapping(value="/deleteFromPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void deleteFromPlaylist(@RequestParam int playlistId, @RequestParam int songId, HttpSession session){
    List<Song> playlistSongs = (List<Song>)session.getAttribute("songList");
    for(Song s:playlistSongs){
      if(s.getSongId()==songId){
        playlistSongs.remove(s);
        break;
      }
    }
    songService.deleteSongFromPlaylist(playlistId,songId);
    session.setAttribute("songList",playlistSongs);
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
  
  @RequestMapping(value = "/viewArtist", method = RequestMethod.GET)
  @ResponseBody
  public void viewArtist(@RequestParam int artistID, HttpSession session){
      Artist artist = artistService.getArtist(artistID);
      List<Album> artistAlbums = (List<Album>) artist.getAlbums();
      List<Song> artistSongs = (List<Song>) artist.getSongs();
      session.setAttribute("currentArtist",artist);
      session.setAttribute("artistSongs",artistSongs);
      session.setAttribute("artistAlbums",artistAlbums);
  }

  @RequestMapping(value = "/viewSongs", method= RequestMethod.GET)
  @ResponseBody
  public void viewSongs(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<Song> followSongs = songService.listAllSongs();
    session.setAttribute("songList",followSongs);
  }
  
  @RequestMapping(value = "/viewFollowedAlbums", method= RequestMethod.GET)
  @ResponseBody
  public void viewFollowedAlbums(HttpSession session){
    /** CURRENTLY VIEWS ALL ALBUMS **/
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
  
  @RequestMapping(value="/upgrade",method=RequestMethod.POST)
  @ResponseBody
  public void upgrade(@RequestParam String cardNumber, @RequestParam String cardHolder, @RequestParam String ccv, @RequestParam int expirationMonth,
      @RequestParam int expirationYear, @RequestParam String creditCompany, @RequestParam String address, HttpSession session)
  {
    System.out.println("madeit");
    User user = (User)session.getAttribute("currentUser");
    user = paymentInfoService.addPayment(user, cardNumber,cardHolder,ccv,expirationMonth,expirationYear,
        creditCompany,address);
    session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value="/cancel",method=RequestMethod.POST)
  @ResponseBody
  public void cancel(HttpSession session){
    System.out.println("In cancel function");
    User user = (User)session.getAttribute("currentUser");
    PaymentInfo paymentInfo = user.getPaymentInfo();
    user = paymentInfoService.deletePayment(user, paymentInfo);
    session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value = "/viewUsers", method= RequestMethod.GET)
  @ResponseBody
  public void viewUsers(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<User> followUsers = userService.listAllUsers();
    session.setAttribute("userList",followUsers);
  }
  
  @RequestMapping(value = "/search", method= RequestMethod.GET)
  @ResponseBody
  public void search(String keyword, HttpSession session){
    List<User> searchUsers = userService.search(keyword);
    List<Album> searchAlbums = albumService.search(keyword);
    List<Artist> searchArtists = artistService.search(keyword);
    List<Song> searchSongs = songService.search(keyword);
    session.setAttribute("userList",searchUsers);
    session.setAttribute("albumList",searchAlbums);
    session.setAttribute("artistList",searchArtists);
    session.setAttribute("songList",searchSongs);
  }
}
