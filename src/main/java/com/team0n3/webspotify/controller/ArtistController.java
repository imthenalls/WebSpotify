
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.RoyaltyPayment;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.RoyaltyPaymentService;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.UserService;
import comparator.SongComparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/artist")
public class ArtistController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private ArtistService artistService;
  @Autowired
  private SongService songService;
  @Autowired
  private RoyaltyPaymentService royaltyPaymentService;
  
  @RequestMapping(value="/followArtist", method=RequestMethod.POST)
  @ResponseBody
  public void followArtist(@RequestParam int artistId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    List<Artist> followedArtists = (List<Artist>)session.getAttribute("followedArtists");
    followedArtists.add(artistService.getArtist(artistId));
    session.setAttribute("followedArtists",followedArtists);
    currentUser = userService.followArtist(currentUser.getUsername(), artistId);
    session.setAttribute("currentUser",currentUser);
  }
  
  @RequestMapping(value="/unfollowArtist", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowArtist(@RequestParam int artistId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    List<Artist> followedArtists = (List<Artist>)session.getAttribute("followedArtists");
    for(Artist a:followedArtists){
      if(a.getArtistId() == artistId){
        followedArtists.remove(a);
        session.setAttribute("followedArtists",followedArtists);
        currentUser = userService.unfollowArtist(currentUser.getUsername(), artistId);
        session.setAttribute("currentUser",currentUser);
        return;
      }
    }
  }
  
  @RequestMapping(value = "/viewArtist", method = RequestMethod.GET)
  @ResponseBody
  public void viewArtist(@RequestParam int artistID, HttpSession session){
      Artist artist = artistService.getArtist(artistID);
      List<Album> artistAlbums = (List<Album>) artist.getAlbums();
      List<Song> popularSongs = (List<Song>) artist.getSongs();
      System.out.println(popularSongs);
      Collections.sort(popularSongs,new SongComparator());
      System.out.println(popularSongs);
      session.setAttribute("currentArtist",artist);
      session.setAttribute("popularSongs",popularSongs);
      session.setAttribute("artistAlbums",artistAlbums);
  }
  
  @RequestMapping( value = "/viewAllArtists", method = RequestMethod.GET)
  @ResponseBody
  public void viewAllArtists(HttpSession session){
    List<Artist> allArtists = artistService.listAllArtists();
    session.setAttribute("allArtists",allArtists);
  }
  
  @RequestMapping( value = "/adminRemoveArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveArtist(@RequestParam int artistId, HttpSession session){
    List<Artist> allArtists = (ArrayList)session.getAttribute("allArtists");
    boolean found = false;
    Artist delete = null;
    for(Artist a : allArtists){
      if(a.getArtistId() == artistId){
        delete = a;
        allArtists.remove(a);
        found = true;
        break;
      }
    }
    if(found){
      if(delete != null)
        userService.adminDeleteArtist(delete);
      session.setAttribute("allArtists",allArtists);
    }
  }
  
  @RequestMapping(value = "/viewUnpaidSongs", method = RequestMethod.GET)
  @ResponseBody
  public void viewUnpaidSongs( HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Artist){
      Artist artist = (Artist)session.getAttribute("currentArtist");
      System.out.println("hello "+artist.toString());
      List<Song> unPaidSongs = royaltyPaymentService.listUnpaidSongsByArtist(artist.getArtistId());
     
      System.out.println("hello size "+unPaidSongs.size());
      for(Song s : unPaidSongs){
        System.out.println(s.toString());
      }
      session.setAttribute("unPaidSongs",unPaidSongs);
    }
  }

  @RequestMapping(value = "/viewPendingRoyaltyPayments", method = RequestMethod.GET)
  @ResponseBody
  public void viewPendingRoyaltyPayments( HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Artist){
      System.out.println("do i get to die yet?");
      Artist artist = (Artist)session.getAttribute("currentArtist");
      List<RoyaltyPayment> paymentRequests = royaltyPaymentService.listUnpaidPaymentsByArtist(artist.getArtistId());
      session.setAttribute("paymentRequests",paymentRequests);
    }else if(user.getAccountType() == AccountType.Admin){
      List<RoyaltyPayment> allPayRequests = royaltyPaymentService.listUnpaidRoyaltyPayments();
      session.setAttribute("paymentRequests",allPayRequests);//REMEMBER TO REMOVE THIS AND ALL OTHER ADMIN SHIT FROM THE SESSION WHEN LOGGING OUT AS ADMIN
    }
  }
  
  @RequestMapping(value = "/requestRoyaltyOnSong", method = RequestMethod.POST)
  @ResponseBody
  public void requestRoyaltyOnSong(@RequestParam int songId, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Artist){
      Artist artist = (Artist)session.getAttribute("currentArtist");
      royaltyPaymentService.artistRequestRoyaltyOnSong(songId, artist.getArtistId());
    }
  }
  
  @RequestMapping(value = "/adminPayArtistRoyalties", method = RequestMethod.POST)
  @ResponseBody
  public void adminPayArtistRoyalties( HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin){
      Artist artist = (Artist)session.getAttribute("currentArtist");
      royaltyPaymentService.adminPayArtist(artist.getArtistId());
    }
  }
  
  @RequestMapping(value = "/adminPaySongRoyalties", method = RequestMethod.POST)
  @ResponseBody
  public void adminPaySongRoyalties(@RequestParam int songId, @RequestParam int artistId, HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin){
      royaltyPaymentService.adminPayArtistBySong(songId, artistId);
    }
  }
  
  @RequestMapping(value = "/adminPayAllRoyalties", method = RequestMethod.POST)
  @ResponseBody
  public void adminPayAllRoyalties(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Admin){
      royaltyPaymentService.adminPayAllArtists();
    }
  }
  
  @RequestMapping( value = "/seeMore", method = RequestMethod.GET)
  @ResponseBody
  public void seeMore(HttpSession session){
    System.out.println((String)session.getAttribute("lastSearch"));
    List<Artist> songs=artistService.search((String)session.getAttribute("lastSearch"), false);
    System.out.println(songs.size());
    session.setAttribute("allSongs", songs);
  }
  
  @RequestMapping(value = "/viewArtistSongs", method = RequestMethod.GET)
  @ResponseBody
  public void viewArtistSongs(HttpSession session){
    User user = (User)session.getAttribute("currentUser");
    if(user.getAccountType() == AccountType.Artist){
      Artist artist = (Artist)session.getAttribute("currentArtist");
      Collection<Song> songs = artist.getSongs();
      session.setAttribute("artistSongs", songs);
    }
  }
  
  @RequestMapping(value = "/artistRequestSongRemoval", method = RequestMethod.POST)
  @ResponseBody
  public void artistRequestSongRemoval(int songId, HttpSession session){
    Artist artist = songService.getSong(songId).getArtistId();
    artistService.artistRequestSongRemoval(songId, artist.getArtistId());
  }
  
  @RequestMapping(value = "/viewSongRemovalRequests", method = RequestMethod.GET)
  @ResponseBody
  public void getSongRemovalRequests(HttpSession session){
    List<Song> removals = artistService.getSongRemovalRequests();
    session.setAttribute("removalRequests",removals);
  }
}
