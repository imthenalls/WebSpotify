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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
  
  private List<Playlist> createdPlaylists = new ArrayList<Playlist>();
  private List<Playlist> followedPlaylists = new ArrayList<Playlist>();
  
  private SongPlayer player = new SongPlayer();
 
  //need a play method here and next/prev method
  @RequestMapping(value="/", method=RequestMethod.GET)
  public ModelAndView handleRequest(HttpSession session) {
    ModelAndView model = new ModelAndView("login");
    return model;
  }

  @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
  public ModelAndView loginUser(@RequestParam String username, @RequestParam String password, HttpSession session){
    User user = userService.login(username, password);
    if(user==null){
        session.setAttribute("badLogin",true);
        return new ModelAndView("redirect:/");
    }
    
    if(user.getAccountType() == AccountType.Admin)
    {
        session.setAttribute("currentUser", user);
        ModelAndView model= new ModelAndView("redirect:/viewAdminBrowse");
        return model;   
    }else if(user.getAccountType() == AccountType.Artist)
    {
        session.setAttribute("currentUser", user);
        ModelAndView model = new ModelAndView("redirect:/viewBrowse");
        return model;   
    }
    createdPlaylists.addAll(user.getCreatedPlaylists());
    followedPlaylists.addAll(user.getFollowedPlaylists());
    session.setAttribute("currentUser", user);
    session.setAttribute("createdPlaylists",createdPlaylists);
    session.setAttribute("followedPlaylists", followedPlaylists);
    ModelAndView model= new ModelAndView("redirect:/viewBrowse");
    return model;   
  }

  @RequestMapping(value = "/viewSignup", method = RequestMethod.GET)
  public ModelAndView viewSignup(HttpSession session) {
    ModelAndView model;
    if(null == session.getAttribute("currentUser")){
      model = new ModelAndView("signup");
      return model;    
    }
    model = new ModelAndView("browse");
    return model;
  }

  @RequestMapping(value = "/signupUser", method = RequestMethod.POST)
  public ModelAndView signupUser(@RequestParam String username, @RequestParam String email, @RequestParam String password,HttpSession session) {
    String errorMessage = userService.signup(username, password, email);
    if(errorMessage.equals("duplicate")){
      session.setAttribute("duplicate",true);
      session.setAttribute("invalidEmail",false);
      return new ModelAndView("signup");
    }
    if(errorMessage.equals("invalidEmail")){
      session.setAttribute("invalidEmail",true);
      session.setAttribute("duplicate",false);
      return new ModelAndView("signup");
    }
    if(errorMessage.equals("hashing")){
      session.setAttribute("invalidEmail",false);
      session.setAttribute("duplicate",false);
      return new ModelAndView("signup");
    }
    return new ModelAndView("redirect:/");
  }

  @RequestMapping(value = "/viewBrowse", method = RequestMethod.GET)
  public ModelAndView viewBrowse(HttpSession session) {
    if(session.getAttribute("currentUser") == null){
      return new ModelAndView("login");
    }
    return new ModelAndView("browse");
  }
  
  @RequestMapping(value = "/viewAdminBrowse", method = RequestMethod.GET)
  public ModelAndView viewAdminBrowse(HttpSession session) {
    if(session.getAttribute("currentUser")==null){
      return new ModelAndView("login");
    }
    return new ModelAndView("admin_browse");
  }
  /*
  @RequestMapping(value = "/artist_browse", method = RequestMethod.GET)
  public ModelAndView artist_browse(HttpSession session) {
    if(session.getAttribute("currentUser")==null){
      return new ModelAndView("login");
    }
    return new ModelAndView("");
  }
  */
  @RequestMapping(value = "/createPlaylist", method = RequestMethod.POST)
  @ResponseBody
  public void createPlaylist(@RequestParam String playlistName, @RequestParam String imagePath, @RequestParam String description, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    Playlist playlist = playlistService.createPlaylist(playlistName,imagePath,description,currentUser);
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
    for(Playlist p:createdPlaylists){
      if(p.getPlaylistID()== playlist.getPlaylistID()){
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
  }
  
  @RequestMapping(value="/deleteSongFromPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void deleteSongFromPlaylist(@RequestParam int playlistId, @RequestParam int songId, HttpSession session){
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
  
  @RequestMapping(value="/followArtist", method=RequestMethod.POST)
  @ResponseBody
  public void followArtist(@RequestParam int artistId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    (currentUser.getFollowedArtists()).add(artistService.getArtist(artistId));
    userService.followArtist(currentUser.getUsername(), artistId);
  }
  
  @RequestMapping(value="/unfollowArtist", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowArtist(@RequestParam int artistId, HttpSession session){
    boolean found = false;
    User currentUser = (User)session.getAttribute("currentUser");
    Collection<Artist> followedArtists = currentUser.getFollowedArtists();
    for(Artist a:followedArtists){
      if(a.getArtistId() == artistId){
        followedArtists.remove(a);
        found = true;
        break;
      }
    }
    if(found)
      userService.unfollowArtist(currentUser.getUsername(), artistId);
  }
  
  @RequestMapping(value="/followSong", method=RequestMethod.POST)
  @ResponseBody
  public void followSong(@RequestParam int songId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    (currentUser.getFollowedSongs()).add(songService.getSong(songId));
    userService.followSong(currentUser.getUsername(), songId);
  }
  
  @RequestMapping(value="/unfollowSong", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowSong(@RequestParam int songId, HttpSession session){
    boolean found = false;
    User currentUser = (User)session.getAttribute("currentUser");
    Collection<Song> followedSongs = currentUser.getFollowedSongs();
    for(Song s:followedSongs){
      if(s.getSongId() == songId){
        followedSongs.remove(s);
        found = true;
        break;
      }
    }
    if(found)
      userService.unfollowSong(currentUser.getUsername(), songId);
  }
  
  @RequestMapping(value="/followAlbum", method=RequestMethod.POST)
  @ResponseBody
  public void followAlbum(@RequestParam int albumId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    (currentUser.getFollowedAlbums()).add(albumService.getAlbum(albumId));
    userService.followAlbum(currentUser.getUsername(), albumId);
  }
  
  @RequestMapping(value="/unfollowAlbum", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowAlbum(@RequestParam int albumId, HttpSession session){
    boolean found = false;
    User currentUser = (User)session.getAttribute("currentUser");
    Collection<Album> followedAlbums = currentUser.getFollowedAlbums();
    for(Album a:followedAlbums){
      if(a.getAlbumId() == albumId){
        followedAlbums.remove(a);
        found = true;
        break;
      }
    }
    if(found)
      userService.unfollowAlbum(currentUser.getUsername(), albumId);
  }
  
  @RequestMapping(value="/followPlaylist", method=RequestMethod.POST)
  @ResponseBody
  public void followPlaylist(@RequestParam int playlist, HttpSession session){
      User currentUser = (User)session.getAttribute("currentUser");
      followedPlaylists.add(playlistService.getPlaylist(playlist));
      userService.followPlaylist(currentUser.getUsername(), playlist);
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

  @RequestMapping(value = "/viewAllSongs", method= RequestMethod.GET)
  @ResponseBody
  public void viewAllSongs(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<Song> followSongs = songService.listAllSongs();
    session.setAttribute("songList",followSongs);
  }
  @RequestMapping(value = "/viewAdminAllSongs", method= RequestMethod.GET)
  @ResponseBody
  public void viewAdminAllSongs(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<Song> followSongs = songService.listAllSongs();
    session.setAttribute("allSongs",followSongs);
  }
  
  @RequestMapping(value = "/viewAdminAllAlbums", method= RequestMethod.GET)
  @ResponseBody
  public void viewAdminAllAlbums(HttpSession session){
    /** CURRENTLY VIEWS ALL Albums **/
    List<Album> allAlbums = albumService.listAllAlbums();
    session.setAttribute("allAlbums",allAlbums);
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
  
  @RequestMapping("/logoutUser")
  public String logoutUser(HttpServletRequest request){
    request.getSession().invalidate();
    return "login";
  }
  
  @RequestMapping(value="/upgradeToPremium",method=RequestMethod.POST)
  @ResponseBody
  public void upgradeToPremium(@RequestParam String cardNumber, @RequestParam String cardHolder, @RequestParam String ccv, @RequestParam int expirationMonth,
      @RequestParam int expirationYear, @RequestParam String creditCompany, @RequestParam String address, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    user = paymentInfoService.addNewPayment(user, cardNumber,cardHolder,ccv,expirationMonth,expirationYear,
        creditCompany,address);
    session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value="/cancelPremium",method=RequestMethod.POST)
  @ResponseBody
  public void cancelPremium(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    PaymentInfo paymentInfo = user.getPaymentInfo();
    user = paymentInfoService.deletePayment(user, paymentInfo);
    session.setAttribute("currentUser",user);
  }
  
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

  @RequestMapping(value = "/viewUsers", method= RequestMethod.GET)
  @ResponseBody
  public void viewUsers(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<User> followUsers = userService.listAllUsers();
    session.setAttribute("userList",followUsers);
  }
  
  @RequestMapping(value = "/search", method= RequestMethod.GET)
  @ResponseBody
  public void search(@RequestParam String keyword, HttpSession session){
    List<User> searchUsers = userService.search(keyword);
    List<Album> searchAlbums = albumService.search(keyword);
    List<Artist> searchArtists = artistService.search(keyword);
    List<Song> searchSongs = songService.search(keyword);
    List<Playlist> searchPlaylists = playlistService.search(keyword);
    session.setAttribute("userList",searchUsers);
    session.setAttribute("albumList",searchAlbums);
    session.setAttribute("artistList",searchArtists);
    session.setAttribute("songList",searchSongs);
    session.setAttribute("playlistList",searchPlaylists);
  }
  
  @RequestMapping( value = "/viewAllArtists", method = RequestMethod.GET)
  @ResponseBody
  public void viewAllArtists(HttpSession session){
    List<Artist> allArtists = artistService.listAllArtists();
    session.setAttribute("allArtists",allArtists);
  }
  
  @RequestMapping( value = "/adminAddArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddArtist(@RequestParam String artistName, @RequestParam int popularity, @RequestParam String imagePath, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        System.out.println(user.toString());
        userService.adminAddArtist( user.getUsername(),  artistName, popularity,  imagePath);
    }
  }

  @RequestMapping( value = "/adminRemoveArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveArtist(@RequestParam int artistId, HttpSession session){
    List<Artist> allArtists = artistService.listAllArtists();
    boolean found = false;
    for(Artist a : allArtists){
      if(a.getArtistId() == artistId){
        allArtists.remove(a);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemoveArtist(currentUser.getUsername(), artistId);
      session.setAttribute("allArtists",allArtists);
    }
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
  
  @RequestMapping( value = "/adminAddSong", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddSong(@RequestParam String title, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        userService.adminAddSong(user.getUsername(), title);    
    }
  }
  
  @RequestMapping( value = "/adminRemoveSong", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveSong(@RequestParam int songId, HttpSession session){
    List<Song> allSongs = songService.listAllSongs();
    boolean found = false;
    for(Song s : allSongs){
      if(s.getSongId() == songId){
        allSongs.remove(s);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemoveSong(currentUser.getUsername(), songId);
      session.setAttribute("allSongs",allSongs);
    }
  }
  
  @RequestMapping( value = "/adminAddAlbum", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddAlbum(@RequestParam String albumName,@RequestParam int popularity, @RequestParam String imagePath, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        userService.adminAddAlbum(user.getUsername(),albumName,  popularity,  imagePath);    
    }
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
}
