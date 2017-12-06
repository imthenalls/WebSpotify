
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

@Service
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
    } 
    artist.setTotalRoyalties(total);
    artistDao.updateArtist(artist);
    
  }
  
  @Override
  @Transactional(readOnly = false)
  public RoyaltyPayment artistRequestRoyaltyOnSong(int songId, int artistId){
    Song song = songDao.getSong(songId);
    Artist artist = artistDao.getArtist(artistId);
    if(song.getUnpayedPlays() > 0){
      RoyaltyPayment payment = new RoyaltyPayment(song, artist,song.currentPayOut(),false);
      royaltyPaymentDao.addRoyaltyPayment(payment);
      return payment;
    }
    return null;
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<Song> listUnpaidSongsByArtist(int artistId){
    List<Song> allSongs = songDao.listSongs();
    List<Song> unPaidSongs = new ArrayList();
    for(Song s : allSongs){
      if(s.getUnpayedPlays() > 0)
        unPaidSongs.add(s);
    }
    return unPaidSongs;
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<RoyaltyPayment> listUnpaidPaymentsByArtist(int artistId){
    List<RoyaltyPayment> allPayments = royaltyPaymentDao.listRoyaltyPayments();
    List<RoyaltyPayment> artistPayments = new ArrayList();
    for(RoyaltyPayment r : allPayments){
      if(!(r.isIsPaid()) )
        artistPayments.add(r);
    }
    return artistPayments;
  }
}
