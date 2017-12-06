/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.model.User;

/**
 *
 * @author spike
 */
public interface PaymentInfoService {
  public PaymentInfo getPayment(int paymentId);
  
  public User addNewPayment(User user, String cardNumber, String cardHolder, String ccv,int zipCode, int expirationMonth,
    int expirationYear, String creditCompany, String address);
  
  public User deletePayment(User user, PaymentInfo paymentInfo);
  
  
}
