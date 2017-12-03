package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import javax.servlet.http.HttpSession;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.service.UserService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.AlbumService;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.PaymentInfoService;
import java.util.ArrayList;
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
  private SongService songService;
  @Autowired
  private AlbumService albumService;
  @Autowired
  private ArtistService artistService;
  @Autowired
  private PaymentInfoService paymentInfoService;
 
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
    List<Playlist> createdPlaylists = new ArrayList<>();
    List<Playlist> followedPlaylists = new ArrayList<>();
    List<Album> followedAlbums = new ArrayList<>();
    List<Song> followedSongs = new ArrayList<>();
    
    createdPlaylists.addAll(user.getCreatedPlaylists());
    followedPlaylists.addAll(user.getFollowedPlaylists());
    followedAlbums.addAll(user.getFollowedAlbums());
    followedSongs.addAll(user.getFollowedSongs());
    session.setAttribute("currentUser", user);
    session.setAttribute("createdPlaylists",createdPlaylists);
    session.setAttribute("followedPlaylists", followedPlaylists);
    session.setAttribute("followedAlbums",followedAlbums);
    session.setAttribute("followedSongs",followedSongs);
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

  @RequestMapping(value = "/viewUsers", method= RequestMethod.GET)
  @ResponseBody
  public void viewUsers(HttpSession session){
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
    session.setAttribute("userList",searchUsers);
    session.setAttribute("albumList",searchAlbums);
    session.setAttribute("artistList",searchArtists);
    session.setAttribute("songList",searchSongs);
  }
}
