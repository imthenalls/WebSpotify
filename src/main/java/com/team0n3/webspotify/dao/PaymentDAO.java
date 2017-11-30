/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;

import com.team0n3.webspotify.model.PaymentInfo;

/**
 *
 * @author spike
 */
public interface PaymentDAO {
  
  public void addPayment(PaymentInfo paymentInfo);
  
  public PaymentInfo getPayment(int paymentId);
  
  public void deletePayment(PaymentInfo paymentInfo);
}
