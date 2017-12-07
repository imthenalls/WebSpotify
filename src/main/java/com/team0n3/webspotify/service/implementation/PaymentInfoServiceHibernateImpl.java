/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.PaymentDAO;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PaymentInfoService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author spike
 */
@Service
@Transactional(readOnly=true)
public class PaymentInfoServiceHibernateImpl implements PaymentInfoService{
  
  @Autowired
  private PaymentDAO paymentDao;
  
  @Autowired
  private UserDAO userDao;
  
  @Autowired
  private SessionFactory sessionFactory;
  
  @Override
  public PaymentInfo getPayment(int paymentId){
    PaymentInfo paymentInfo = paymentDao.getPayment(paymentId);
    return paymentInfo;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User addNewPayment(User user, String cardNumber, String cardHolder, String ccv,int zipCode, int expirationMonth,
    int expirationYear, String creditCompany, String address){
    SecureRandom cardRandom = new SecureRandom(),ccvRandom = new SecureRandom();
    byte[] cardSalt = new byte[12], ccvSalt = new byte[12];
    MessageDigest cardMd = null, ccvMd = null;
    try{
      cardMd = MessageDigest.getInstance("SHA-256");
      ccvMd = MessageDigest.getInstance("SHA-256");
    }catch(NoSuchAlgorithmException ex){
      return null;
    }
    cardRandom.nextBytes(cardSalt);
    ccvRandom.nextBytes(ccvSalt);
    cardMd.update(cardSalt);
    ccvMd.update(ccvSalt);
    cardMd.update(cardNumber.getBytes());
    ccvMd.update(ccv.getBytes());
    byte[] hashCard = cardMd.digest();
    byte[] hashCCV = ccvMd.digest();
    int cardLength = cardNumber.length();
    String lastFour = cardNumber.substring(cardLength-4);
    PaymentInfo paymentInfo = new PaymentInfo(hashCard,cardHolder,hashCCV,zipCode, expirationMonth,expirationYear,
    creditCompany,address,lastFour);
    paymentDao.addPayment(paymentInfo);
    user.setPaymentInfo(paymentInfo);
    user.setAccountType(AccountType.Premium);
    userDao.updateUser(user);
    return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public void updatePaymentInfo(User user, String cardNumber, String cardHolder, String ccv,int zipCode, int expirationMonth,
    int expirationYear, String creditCompany, String address){
    SecureRandom cardRandom = new SecureRandom(),ccvRandom = new SecureRandom();
    byte[] cardSalt = new byte[12], ccvSalt = new byte[12];
    MessageDigest cardMd = null, ccvMd = null;
    try{
      cardMd = MessageDigest.getInstance("SHA-256");
      ccvMd = MessageDigest.getInstance("SHA-256");
    }catch(NoSuchAlgorithmException ex){
      //return null;
    }
    cardRandom.nextBytes(cardSalt);
    ccvRandom.nextBytes(ccvSalt);
    cardMd.update(cardSalt);
    ccvMd.update(ccvSalt);
    cardMd.update(cardNumber.getBytes());
    ccvMd.update(ccv.getBytes());
    byte[] hashCard = cardMd.digest();
    byte[] hashCCV = ccvMd.digest();
    int cardLength = cardNumber.length();
    String lastFour = cardNumber.substring(cardLength-4);
    PaymentInfo paymentInfo = user.getPaymentInfo();
    paymentInfo.setAddress(address);
    paymentInfo.setCardHolder(cardHolder);
    paymentInfo.setCardNumber(cardSalt);
    paymentInfo.setCcv(hashCCV);
    paymentInfo.setCreditCompany(creditCompany);
    paymentInfo.setExpirationMonth(expirationMonth);
    paymentInfo.setExpirationYear(expirationYear);
    paymentInfo.setLastFour(lastFour);
    paymentInfo.setZipCode();
    paymentDao.updatePayment(paymentInfo);
    user.setPaymentInfo(paymentInfo);
    userDao.updateUser(user);
    //return user;
  }
  
  @Override
  @Transactional(readOnly = false)
  public User deletePayment(User user,PaymentInfo paymentInfo){
    paymentDao.deletePayment(paymentInfo);
    user.setAccountType(AccountType.Free);
    userDao.updateUser(user);
    return user;
  }
}
