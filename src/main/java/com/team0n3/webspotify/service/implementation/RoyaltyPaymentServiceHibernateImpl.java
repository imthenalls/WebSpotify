
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.dao.RoyaltyPaymentDAO;
import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.RoyaltyPayment;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.RoyaltyPaymentService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("royaltyPaymentService")
@Transactional(readOnly = true)
public class RoyaltyPaymentServiceHibernateImpl implements RoyaltyPaymentService{
  
  @Autowired
  private RoyaltyPaymentDAO royaltyPaymentDao;
  
  @Autowired
  private UserDAO userDao;
  
  @Autowired
  private SongDAO songDao;
  
  @Autowired
  private ArtistDAO artistDao;
  
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  @Transactional(readOnly = true)
  public List<RoyaltyPayment> listAllRoyaltyPayments(){
    return royaltyPaymentDao.listRoyaltyPayments();
  }

  @Override
  @Transactional(readOnly = true)
  public RoyaltyPayment getRoyaltyPayment(int paymentId){
    return royaltyPaymentDao.getRoyaltyPayment(paymentId);
  }
  
  @Override
  @Transactional(readOnly = false)
  public void adminPayArtist(int artistId){
    List<RoyaltyPayment> allPayments = royaltyPaymentDao.listRoyaltyPayments();
    List<RoyaltyPayment> artistPayments = new ArrayList();
    for(RoyaltyPayment r : allPayments){
      if(r.getArtistId().getArtistId() == artistId)
        artistPayments.add(r);
    }
    Artist artist = artistDao.getArtist(artistId);
    int total = artist.getTotalRoyalties();
    for(RoyaltyPayment r : artistPayments){
      total += r.getPaymentAmount();
      r.setIsPaid(true);
      royaltyPaymentDao.updateRoyaltyPayment(r);
    } 
    artist.setTotalRoyalties(total);
    artistDao.updateArtist(artist);
    
  }
  
  @Override
  @Transactional(readOnly = false)
  public RoyaltyPayment artistRequestRoyaltyOnSong(int songId, int artistId){
    Song song = songDao.getSong(songId);
    Artist artist = artistDao.getArtist(artistId);
    System.out.println(song.toString()+" "+artist.toString());
    System.out.println("asdasdasdasdasdasd "+song.currentPayOut());
    RoyaltyPayment payment = new RoyaltyPayment(song, artist, song.currentPayOut(),false);
    royaltyPaymentDao.addRoyaltyPayment(payment);
    return payment;
  }
  
  @Override
  @Transactional(readOnly = true)
  public ArrayList<Song> listUnpaidSongsByArtist(int artistId){
    List<Song> allSongs = songDao.listSongs();
    ArrayList<Song> unPaidSongs = new ArrayList();
    for(Song s : allSongs){
      if(s.getUnpayedPlays() > 0 && s.getArtistId().getArtistId() == artistId){
        unPaidSongs.add(s);
      }
    }
    return unPaidSongs;
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<RoyaltyPayment> listUnpaidPaymentsByArtist(int artistId){
    List<RoyaltyPayment> allPayments = royaltyPaymentDao.listRoyaltyPayments();
    List<RoyaltyPayment> artistPayments = new ArrayList();
    for(RoyaltyPayment r : allPayments){
      if(!(r.isIsPaid())&& r.getArtistId().getArtistId() == artistId )
        artistPayments.add(r);
      System.out.println(r.toString()+"hello");
    }
    return artistPayments;
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<RoyaltyPayment> listAllUnpaidRequests(){
    List<RoyaltyPayment> allPayments = royaltyPaymentDao.listRoyaltyPayments();
    return allPayments;
  }
  
  
  @Override
  @Transactional(readOnly = false)
  public void adminPayArtistBySong(int songId, int artistId){
    List<RoyaltyPayment> allPayments = royaltyPaymentDao.listRoyaltyPayments();
    RoyaltyPayment pay = null;
    for(RoyaltyPayment r : allPayments){
      if(!r.isIsPaid() && r.getArtistId().getArtistId() == artistId && r.getSongId().getSongId() == songId)
        pay = r;
    }
    Artist artist = artistDao.getArtist(artistId);
    int total = artist.getTotalRoyalties();
    if(pay != null){
      total += pay.getPaymentAmount();
      pay.setIsPaid(true);
      royaltyPaymentDao.updateRoyaltyPayment(pay);
      artist.setTotalRoyalties(total);
      artistDao.updateArtist(artist);
    }
  }
  
}
