
package com.team0n3.webspotify.dao.implementation;

import java.util.List;
import com.team0n3.webspotify.dao.RoyaltyPaymentDAO;
import com.team0n3.webspotify.model.RoyaltyPayment;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

public class RoyaltyPaymentDAOHibernateImpl implements RoyaltyPaymentDAO{
  
  @Autowired
  private SessionFactory sessionFactory;
  
  public RoyaltyPaymentDAOHibernateImpl(SessionFactory sessionFactory){
    this.sessionFactory=sessionFactory;
  }

  @Override
  public void addRoyaltyPayment(RoyaltyPayment payment){
    sessionFactory.getCurrentSession().persist(payment);
  }

  @Override
  public RoyaltyPayment getRoyaltyPayment(int paymentId){
    RoyaltyPayment p = (RoyaltyPayment)sessionFactory.getCurrentSession().get(RoyaltyPayment.class,paymentId);
    return p;
  }

  @Override
  public List<RoyaltyPayment> listRoyaltyPayments(){
    List<RoyaltyPayment> paymentList = sessionFactory.getCurrentSession().createCriteria(RoyaltyPayment.class).list();
    return paymentList;
  }

  @Override
  public void deleteRoyaltyPayment(RoyaltyPayment payment){
    sessionFactory.getCurrentSession().delete(payment);
  }
  
  @Override
  public void updateRoyaltyPayment(RoyaltyPayment payment) {
    sessionFactory.getCurrentSession().update(payment);
  }

}
