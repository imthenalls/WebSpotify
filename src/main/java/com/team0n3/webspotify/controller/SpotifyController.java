package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Ad;
import javax.servlet.http.HttpSession;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.model.RoyaltyPayment;
import com.team0n3.webspotify.service.AdService;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.AlbumService;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.PaymentInfoService;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.RoyaltyPaymentService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Collection;
/**
 * Handles requests for the application home page.
 */
@Controller
public class SpotifyController {

  @Autowired
  private UserService userService;
  @Autowired
  private SongService songService;
  @Autowired
  private AlbumService albumService;
  @Autowired
  private ArtistService artistService;
  @Autowired
  private PlaylistService playlistService;
  @Autowired
  private PaymentInfoService paymentInfoService;
  @Autowired
  private AdService adService;
 
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
    
    if(user.getAccountType() == AccountType.Admin){
        session.setAttribute("currentUser", user);
        ModelAndView model= new ModelAndView("redirect:/viewAdminBrowse");
        session.setAttribute("viewedUser", user);
        return model;   
    }
    if(user.getAccountType() == AccountType.Artist){
      session.setAttribute("currentUser", user);
      ModelAndView model= new ModelAndView("redirect:/viewArtistBrowse");
      List<Artist> allArtists = artistService.listAllArtists();
      Artist artist = null;
      for(Artist a : allArtists){       
        if(a.getUser() != null){
          if((a.getUser().getUsername()).equals(username)){
            artist = a;
            session.setAttribute("currentArtist", a);
            session.setAttribute("viewedUser", user);
            break;
          }
        }
      }
      
      List<Song> allSongs = new ArrayList();
      allSongs.addAll(songService.listAllSongs());
      session.setAttribute("allSongs",allSongs);
      List<Song> artistSongs = new ArrayList();
      for(Song s : allSongs){
        if(s.getArtistId().getArtistId() == ((Artist)session.getAttribute("currentArtist")).getArtistId()){
          artistSongs.add(s);
        }
      }       
      session.setAttribute("artistSongs", artistSongs);
      return model;
    }
    
    if(user.getAccountType() == AccountType.Banned){
      return(new ModelAndView("redirect:/viewBanned"));
    }
    
    List<String> allGenres = new ArrayList();
    List<Playlist> createdPlaylists = new ArrayList<>();
    List<Playlist> followedPlaylists = new ArrayList<>();
    List<Album> followedAlbums = new ArrayList<>();
    List<Song> followedSongs = new ArrayList<>();
    List<Artist> followedArtists = new ArrayList<>();
    List<User> followedUsers = new ArrayList();
    List<User> followerUsers = new ArrayList();
    
    createdPlaylists.addAll(user.getCreatedPlaylists());
    followedPlaylists.addAll(user.getFollowedPlaylists());
    followedAlbums.addAll(user.getFollowedAlbums());
    followedSongs.addAll(user.getFollowedSongs());
    followedArtists.addAll(user.getFollowedArtists());
    followedUsers.addAll(user.getFollowing());
    followerUsers.addAll(user.getFollowers());
    allGenres.addAll(songService.getGenreList());
    
    session.setAttribute("currentUser", user);
    session.setAttribute("createdPlaylists",createdPlaylists);
    session.setAttribute("followedPlaylists", followedPlaylists);
    session.setAttribute("followedAlbums",followedAlbums);
    session.setAttribute("followedSongs",followedSongs);
    session.setAttribute("followedArtists",followedArtists);
    session.setAttribute("followedUsers",followedUsers);
    session.setAttribute("followerUsers",followerUsers);
    session.setAttribute("allGenres",allGenres);
    session.setAttribute("ad", adService.randomAd());
    
    List<Album> topAlbums = albumService.getTopAlbums();
    List<Playlist> topPlaylists = playlistService.getTopPlaylists();
    List<Artist> topArtists = artistService.getTopArtists();
    List<Song> topSongs = songService.getTop50Songs();
    
    session.setAttribute("topAlbums", topAlbums);
    session.setAttribute("topPlaylists", topPlaylists);
    session.setAttribute("topArtists", topArtists);
    session.setAttribute("topSongs", topSongs);    

    
    List<Artist> newArtists = artistService.getNewArtists();
    List<Album> newAlbums = albumService.getNewAlbums();
      
    session.setAttribute("newArtists", newArtists);
    session.setAttribute("newAlbums", newAlbums);
    
    List<Album> nonFollowAlbum= albumService.getNotFollowedAlbums(username);
    List<Artist> nonFollowArtist= artistService.getNotFollowedArtists(username);
    
    session.setAttribute("discoverAlbums", nonFollowAlbum);
    session.setAttribute("discoverArtists", nonFollowArtist);
    List<User> userList = userService.listAllUsers();
    session.setAttribute("userList",userList);
    
    session.setMaxInactiveInterval(45*60); //set the inactive timeout to 45 minutes
    
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
  public ModelAndView signupUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, 
          @RequestParam String artist, HttpSession session) {

    boolean isArtist = !(artist.equals("false"));
    String errorMessage = userService.signup(username, password, email, isArtist);
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
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public void addUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, 
          @RequestParam String artist, HttpSession session) {
    boolean isArtist = !(artist.equals("false"));
    userService.addUser(username, password, email, isArtist);
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
  @RequestMapping(value = "/viewArtistBrowse", method = RequestMethod.GET)
  public ModelAndView viewArtistBrowse(HttpSession session) {
    if(session.getAttribute("currentUser")==null){
      return new ModelAndView("login");
    }
    return new ModelAndView("artist_browse");
  }
  
  @RequestMapping(value = "/viewBanned", method = RequestMethod.GET)
  public ModelAndView viewBanned(HttpSession session) {
    return new ModelAndView("banned");
  }
  
  @RequestMapping("/logoutUser")
  public String logoutUser(HttpServletRequest request){
    request.getSession().invalidate();
    return "login";
  }
 
  @RequestMapping(value="/upgradeToPremium",method=RequestMethod.POST)
  @ResponseBody
  public void upgradeToPremium(@RequestParam String cardNumber, @RequestParam String cardHolder, @RequestParam String ccv, @RequestParam int expirationMonth,
      @RequestParam int expirationYear, @RequestParam String creditCompany, @RequestParam String address, @RequestParam int zipCode, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    user = paymentInfoService.addNewPayment(user, cardNumber,cardHolder, ccv, zipCode, expirationMonth, expirationYear,
        creditCompany,address);
    session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value="/editPaymentInfo",method=RequestMethod.POST)
  @ResponseBody
  public void editPaymentInfo(@RequestParam String cardNumber, @RequestParam String cardHolder, @RequestParam String ccv, @RequestParam int expirationMonth,
      @RequestParam int expirationYear, @RequestParam String creditCompany, @RequestParam String address, @RequestParam int zipCode, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    paymentInfoService.updatePaymentInfo(user, cardNumber,cardHolder, ccv, zipCode, expirationMonth, expirationYear,
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

  @RequestMapping(value = "/viewUsers", method= RequestMethod.GET)
  @ResponseBody
  public void viewUsers(HttpSession session){
    List<User> userList = userService.listAllUsers();
    session.setAttribute("userList",userList);
  }
  
  @RequestMapping(value = "/search", method= RequestMethod.GET)
  @ResponseBody
  public void search(@RequestParam String keyword, HttpSession session){
    List<User> searchUsers = userService.search(keyword,true);
    List<Album> searchAlbums = albumService.search(keyword,true);
    List<Artist> searchArtists = artistService.search(keyword,true);
    List<Song> searchSongs = songService.search(keyword,true);
    List<Playlist> searchPlaylists = playlistService.search(keyword,true);
    if(searchArtists.size()!=0){ //Artist found with search term
      session.setAttribute("topResultArtist", searchArtists.get(0));
      session.setAttribute("topResultSong", null);
    }
    else if(searchSongs.size()!=0){ //Song found with search term
      session.setAttribute("topResultSong", searchSongs.get(0));
      session.setAttribute("topResultArtist", null);
    }
    session.setAttribute("userList",searchUsers);
    session.setAttribute("albumList",searchAlbums);
    session.setAttribute("artistList",searchArtists);
    session.setAttribute("songList",searchSongs);
    session.setAttribute("publicPlaylists", searchPlaylists);
    session.setAttribute("lastSearch",keyword);
  }
  
  @RequestMapping( value = "/adminViewUnapprovedUsers", method = RequestMethod.GET)
  @ResponseBody
  public void adminViewUnapprovedUsers(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin)
    {
       List<User> allUsers = userService.listAllUsers();
       List<User> unapprovedUsers = new ArrayList<>();
       for(User u:allUsers){
           if(u.getAccountType() == AccountType.Unapproved)
           {
               unapprovedUsers.add(u);
           }
       }
       session.setAttribute("unapprovedUsers",unapprovedUsers);
    }
  }
  
  @RequestMapping( value = "/adminViewUnapprovedArtists", method = RequestMethod.GET)
  @ResponseBody
  public void adminViewUnapprovedArtists(HttpSession session){  
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin)
    {
       List<User> allUsers = userService.listAllUsers();
       List<User> unapprovedArtists = new ArrayList<>();
       for(User u:allUsers){
           if(u.getAccountType() == AccountType.UnapprovedArtist)
           {
               unapprovedArtists.add(u);
           }
       }
       session.setAttribute("unapprovedArtists",unapprovedArtists);
    }
  }
  
  @RequestMapping( value = "/adminApproveUser", method = RequestMethod.POST)
  @ResponseBody
  public void adminApproveUser(@RequestParam String username, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin){
      List<User> unapprovedUsers = (ArrayList)session.getAttribute("unapprovedUsers");
      User approvedUser = userService.getUser(username);
      unapprovedUsers.remove(approvedUser);
      userService.adminApproveFreeUser(username);
      session.setAttribute("unapprovedUsers",unapprovedUsers);
    }     
  }
  
  @RequestMapping( value = "/adminApproveArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminApproveArtist(@RequestParam String username, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin){
      List<User> unapprovedArtists = (ArrayList)session.getAttribute("unapprovedArtists");
      User approvedArtist = userService.getUser(username);
      unapprovedArtists.remove(approvedArtist);
      userService.adminApproveArtistUser(username);
      session.setAttribute("unapprovedArtists",unapprovedArtists);
    }     
  }
  
  @RequestMapping(value = "/adminRemoveUser", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveUser(@RequestParam String username, HttpSession session){
    //do later
  }
  @RequestMapping(value = "/changeProfPic", method = RequestMethod.POST)
  @ResponseBody
  public void changeProfPic(@RequestParam String path, HttpSession session){
      User user = (User)session.getAttribute("currentUser");
      userService.changeProfilePic(user.getUsername(), path);
      user.setImagePath(path);
      session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
  @ResponseBody
  public boolean deleteAccount(@RequestParam String password, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    boolean b = userService.removeUser(user.getUsername(), password);
    return b;
  }
  
  @RequestMapping(value = "/ad", method = RequestMethod.GET)
  @ResponseBody
  public String getAd(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Premium)
    {
      return "";
    }
    return adService.randomAd().getImagePath();
  }
  
  @RequestMapping(value = "/viewUserProfile", method = RequestMethod.GET)
  @ResponseBody
  public void getProfile(@RequestParam String username, HttpSession session){
    session.setAttribute("viewedUser", userService.getUser(username));
  }
  
  @RequestMapping(value = "/banUser", method = RequestMethod.POST)
  @ResponseBody
  public void banUser(@RequestParam String username, HttpSession session){
    userService.banUser(username);
    List<User> userList = userService.listAllUsers();  
    session.setAttribute("userList",userList);
  }
  
   @RequestMapping( value = "/seeMore", method = RequestMethod.GET)
  @ResponseBody
  public void seeMore(HttpSession session){
    List<User> users=userService.search((String)session.getAttribute("lastSearch"), false);
    session.setAttribute("userList", users);
  }
  
  
  @RequestMapping( value = "/viewAds", method = RequestMethod.GET)
  @ResponseBody
  public void adminViewAds(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin)
    {
       List<Ad> ads = adService.listAllAds();
       session.setAttribute("adList",ads);
    }
  }
  @RequestMapping( value = "/followUser", method = RequestMethod.POST)
  @ResponseBody
  public void followUser(@RequestParam String username, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    List<User> followedUsers = (List<User>)session.getAttribute("followedUsers");
    followedUsers.add(userService.getUser(username));
    session.setAttribute("followedUsers",followedUsers);
    user = userService.followUser(user.getUsername(),  username);
    session.setAttribute("currentUser",user);
  }
  
  @RequestMapping(value="/unfollowUser", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowUser(@RequestParam String username, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    List<User> followedUsers = (List<User>)session.getAttribute("followedUsers");
    for(User s:followedUsers){
      if(s.getUsername().equals(username)){
        currentUser = userService.unfollowUser(currentUser.getUsername(), username);
        followedUsers.remove(s);
        session.setAttribute("followedUsers",followedUsers);
        session.setAttribute("currentUser",currentUser);
        return;
      }
    }
  }
  
  @RequestMapping( value = "/adminDeleteUser", method = RequestMethod.POST)
  @ResponseBody
  public void adminDeleteUser(@RequestParam String username, HttpSession session){
    User user = userService.getUser(username);
    userService.adminDeleteUser(user);
    List<User> userList = userService.listAllUsers();  
    session.setAttribute("userList",userList);
  }
  
  
  @RequestMapping( value = "/viewFollowers", method = RequestMethod.GET)
  @ResponseBody
  public void viewFollowers(HttpSession session){
    User user = (User)session.getAttribute("viewedUser");
    session.setAttribute("userList", user.getFollowers());
  }
  
   @RequestMapping( value = "/viewFollowing", method = RequestMethod.GET)
  @ResponseBody
  public void viewFollowing(HttpSession session){
    User user = (User)session.getAttribute("viewedUser");
    session.setAttribute("userList", user.getFollowing());
  }
}
