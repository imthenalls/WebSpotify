/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.model.Ad;
import com.team0n3.webspotify.service.AdService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.dao.AdDAO;

@Service
@Transactional(readOnly = true)
public class AdServiceHibernateImpl implements AdService{
  @Autowired
  private AdDAO adDao;
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Override
  public Ad getad(int adId) {
    return adDao.getad(adId);
  }

  @Override
  @Transactional(readOnly = false)
  public void addAd(Ad ad) {
    adDao.addAd(ad);
  }

  @Override
  public List<Ad> listAllAds() {
    return adDao.listAllAds();
  }

  @Override
  @Transactional(readOnly = false)
  public void updateAd(Ad ad) {
    adDao.updateAd(ad);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteAd(Ad ad) {
    adDao.deleteAd(ad);
  }

  @Override
  public Ad randomAd() {
    return adDao.randomAd();
  }
  
}
