/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author spike
 */
@Entity
@Table(name="paymentInfo")
public class PaymentInfo implements Serializable{
  
  @Id
  @Column(name="paymentId",nullable=false)
  @GeneratedValue
  private int paymentId;
  
  @Column(name="cardNumber",nullable=false)
  private byte[] cardNumber;
  
  @Column(name="cardHolder",nullable=false)
  private String cardHolder;
  
  @Column(name="ccv",nullable=false)
  private byte[] ccv;
  
  @Column(name="expirationMonth",nullable=false)
  private int expirationMonth;
  
  @Column(name="expirationYear",nullable=false)
  private int expirationYear;
  
  @Column(name="creditCompany",nullable=false)
  private String creditCompany;
  
  @Column(name="address",nullable=false)
  private String address;
  
  @Column(name="lastFour",nullable=false)
  private String lastFour;
  
  @OneToMany(cascade=CascadeType.ALL,mappedBy="paymentInfo")
  private Collection<User> usersUsingCard;
  
  public PaymentInfo(){
    
  }
  
  public PaymentInfo(byte[] cardNumber,  String cardHolder, byte[] ccv, int expirationMonth, int expirationYear, String creditCompany, String address,String lastFour){
    this.cardNumber=cardNumber;
    this.cardHolder=cardHolder;
    this.ccv=ccv;
    this.expirationMonth=expirationMonth;
    this.expirationYear=expirationYear;
    this.creditCompany=creditCompany;
    this.address=address;
    this.lastFour = lastFour;
  }

  public byte[] getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(byte[] cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public byte[] getCcv() {
    return ccv;
  }

  public void setCcv(byte[] ccv) {
    this.ccv = ccv;
  }

  public int getExpirationMonth() {
    return expirationMonth;
  }

  public void setExpirationMonth(int expirationMonth) {
    this.expirationMonth = expirationMonth;
  }
  
  public int getExpirationYear(){
    return expirationYear;
  }
  
  public void setExpirationYear(int expirationYear){
    this.expirationYear = expirationYear;
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
  
  public String getLastFour(){
    return lastFour;
  }
  
  public void setLastFour(String lastFour){
    this.lastFour = lastFour;
  }
}
