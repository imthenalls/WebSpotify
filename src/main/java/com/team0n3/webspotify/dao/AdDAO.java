/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;

import com.team0n3.webspotify.model.Ad;
import java.util.List;

/**
 *
 * @author JSCHA
 */
public interface AdDAO {
  public Ad getad(int adId);
  
  public void addAd(Ad ad); 
  
  public List<Ad> listAllAds();
  
  public void updateAd(Ad ad);

  public void deleteAd(Ad ad);
  
  public Ad randomAd();
}
