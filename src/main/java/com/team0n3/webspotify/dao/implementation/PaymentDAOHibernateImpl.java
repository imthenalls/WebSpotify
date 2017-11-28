/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.PaymentDAO;
import com.team0n3.webspotify.model.PaymentInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author spike
 */
public class PaymentDAOHibernateImpl implements PaymentDAO{
  
  @Autowired
  private SessionFactory sessionFactory;
  
  public PaymentDAOHibernateImpl(SessionFactory sessionFactory){
    this.sessionFactory=sessionFactory;
  }
      
  @Override
  public void addPayment(PaymentInfo paymentInfo){
    sessionFactory.getCurrentSession().persist(paymentInfo);
  }
  
  @Override
  public PaymentInfo getPayment(int paymentId){
    PaymentInfo paymentInfo = (PaymentInfo)sessionFactory.getCurrentSession().get(PaymentInfo.class,paymentId);
    return paymentInfo;
  }
  
  public void deletePayment(PaymentInfo paymentInfo){
    sessionFactory.getCurrentSession().delete(paymentInfo);
  }
}
