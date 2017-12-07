
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.AdDAO;
import com.team0n3.webspotify.model.Ad;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AdDAOHibernateImpl implements AdDAO{   
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${song.maxResult}")
  private int maxResults;
  
  public AdDAOHibernateImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addAd(Ad ad) {
    sessionFactory.getCurrentSession().persist(ad);
  }

  @Override
  public Ad getad(int adId){
    Ad ad = (Ad)sessionFactory.getCurrentSession().get(Ad.class,adId);
    return ad;
  }

  @Override
  public List<Ad> listAllAds() {
    List<Ad> adList = sessionFactory.getCurrentSession().createCriteria(Ad.class).list();
    
    return adList;
  }

  @Override
  public void updateAd(Ad ad) {
    sessionFactory.getCurrentSession().update(ad);
  }
  
  @Override
  public void deleteAd(Ad ad) {
    sessionFactory.getCurrentSession().delete(ad);
  }

  @Override
  public Ad randomAd() {
    Criteria c = sessionFactory.getCurrentSession().createCriteria(Ad.class);
    c.add(Restrictions.eq( "active", true ));
    c.add(Restrictions.sqlRestriction("1=1 order by rand()"));
    c.setMaxResults(1);
    return (Ad)c.list().get(0);
  }
}

