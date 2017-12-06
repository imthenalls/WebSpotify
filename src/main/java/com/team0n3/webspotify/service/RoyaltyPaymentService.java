
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.RoyaltyPayment;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.List;

public interface RoyaltyPaymentService {
  
  public void adminPayArtist(int artistId);
  public RoyaltyPayment artistRequestRoyaltyOnSong(int songId, int artistId);
  public List<RoyaltyPayment> listUnpaidPaymentsByArtist(int artistId);
  public List<Song> listUnpaidSongsByArtist(int artistId);
  public List<RoyaltyPayment> listAllRoyaltyPayments();
  public RoyaltyPayment getRoyaltyPayment(int paymentId);
  public void adminPayArtistBySong(int songId, int artistId);
  public List<RoyaltyPayment> listAllUnpaidRequests();
  
}
