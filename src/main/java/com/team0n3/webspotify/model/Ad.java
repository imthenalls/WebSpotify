
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="ads")
public class Ad implements Serializable{

  @Id
  @Column(name="adId", nullable=false)
  @GeneratedValue
  private int adId;

  @Column(name="path", nullable=false)
  private String imagePath;

  @Column(name="active", nullable=false)
  private boolean active;

  public Ad(int adId, String imagePath, boolean active) {
    this.adId = adId;
    this.imagePath = imagePath;
    this.active = active;
  }

  public Ad() {
  }

  
  public int getAdId() {
    return adId;
  }

  public void setAdId(int adId) {
    this.adId = adId;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
  
  @Override
  public String toString(){
    return "ad= "+adId;
  }
}
