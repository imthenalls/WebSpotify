/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author spike
 */
@Entity
@Table(name="creditCardInfo")
public class CreditCardInfo implements Serializable{
  
  @Id
  @Column(name="cardNumber",nullable=false)
  private String cardNumber;
  
  @Column(name="cardHolder",nullable=false)
  private String cardHolder;
  
  @Column(name="ccv",nullable=false)
  private String ccv;
  
  @Column(name="expirationDate",nullable=false)
  private Date expirationDate;
  
  @Column(name="creditCompany",nullable=false)
  private String creditCompany;
  
  @Column(name="address",nullable=false)
  private String address;
  
  public CreditCardInfo(){
    
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public String getCcv() {
    return ccv;
  }

  public void setCcv(String ccv) {
    this.ccv = ccv;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getCreditCompany() {
    return creditCompany;
  }

  public void setCreditCompany(String creditCompany) {
    this.creditCompany = creditCompany;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }  
}
