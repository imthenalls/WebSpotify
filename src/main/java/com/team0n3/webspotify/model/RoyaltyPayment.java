
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "royaltypayment")
public class RoyaltyPayment implements Serializable {
  @Id
  @Column(name = "paymentid",nullable=false)
  @GeneratedValue
  private int paymentId;
  
  @ManyToOne
  @JoinColumn(name = "songId",referencedColumnName = "songId",nullable=false)
  private Song songId;
  
  @ManyToOne
  @JoinColumn(name = "artistId",referencedColumnName = "artistId",nullable=false)
  private Artist artistId;
  
  @Column(name = "paymentamount")
  private int paymentAmount;
  
  @Column(name = "isPaid")
  private boolean isPaid; 

  public RoyaltyPayment(){}
  
  public RoyaltyPayment(Song songId, Artist artistId, int paymentAmount, boolean isPaid){
    this.songId = songId;
    this.artistId = artistId;
    this.paymentAmount = paymentAmount;
    this.isPaid = isPaid;
  }

  public boolean isIsPaid() {
    return isPaid;
  }

  public void setIsPaid(boolean isPaid) {
    this.isPaid = isPaid;
  }
  
  public int getPaymentId() {
    return paymentId;
  }
  
  public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;
  }

  public Song getSongId() {
    return songId;
  }

  public void setSongId(Song songId) {
    this.songId = songId;
  }

  public Artist getArtistId() {
    return artistId;
  }

  public void setArtistId(Artist artistId) {
    this.artistId = artistId;
  }

  public int getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(int paymentAmount) {
    this.paymentAmount = paymentAmount;
  }
  
  @Override
    public String toString(){
        return "payment id="+paymentId+", song id="+songId+", artist id="+artistId;
    }
  
}
