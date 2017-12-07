package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Ad;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.Collection;
import java.util.List;

public interface AdService {
  
  public Ad getad(int adId);
  
  public void addAd(Ad ad); 
  
  public List<Ad> listAllAds();
  
  public void updateAd(Ad ad);

  public void deleteAd(Ad ad);
  
  public Ad randomAd();
 
}
