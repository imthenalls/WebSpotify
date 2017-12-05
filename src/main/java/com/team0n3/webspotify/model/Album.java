
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

@Entity
@Table(name="albums")
public class Album implements Serializable{

  @Id
  @Column(name="albumId", nullable=false)
  @GeneratedValue
  private int albumId;

  @Column(name="albumname", nullable=false)
  private String albumName;

  @OneToMany(cascade=CascadeType.ALL,mappedBy="albumId")
  private Collection<Song> songs;
  
  @Column(name="duration", nullable=true)
  private int duration;
          
  @Column(name="popularity", nullable=true)
  private int popularity;
  
  @Column(name="imagePath", nullable=true)
  private String imagePath;
  
  @ManyToOne
  @JoinColumn(name="artistId",referencedColumnName="artistId",nullable=false)
  private Artist artistId;
  
  @ManyToMany(cascade ={CascadeType.PERSIST, 
  CascadeType.MERGE }, mappedBy = "followedAlbums")
  private Collection<User> followers;
  public Album() {
  }

  public Album(String albumName) {
    this.albumName = albumName;
    this.songs = null;
  }
  
  public Album(String albumName, int popularity, String imagePath) {
    this.albumName = albumName;
    this.popularity = popularity;
    this.imagePath = imagePath;
    this.songs = null;
  }
  public int getAlbumId()
  {
    return albumId;
  }
  public void setAlbumId(int albumId)
  {
    this.albumId = albumId;
  }

  public Collection<Song> getSongs() {
    return songs;
  }

  public void setSongs(Collection<Song> songs) {
    this.songs = songs;
  }

  public String getAlbumName() {
    return this.albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public Artist getArtistId() {
      return artistId;
  }
  
  public void setArtistId(Artist artistId) {
      this.artistId = artistId;
  }
  
  public int getPopularity(){
      return this.popularity;
  }
  
  public void setPopularity(int popularity){
      this.popularity = popularity;
  }

  public String getImagePath() {
      return imagePath;
  }

  public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
  }

  public int getDuration() {
      return duration;
  }

  public void setDuration(int duration) {
      this.duration = duration;
  }
  
  public Collection<User> getFollowers() {
      return followers;
  }

  public void setFollowers(Collection<User> followers) {
      this.followers = followers;
  }
  
  @Override
  public String toString(){
    return "Album = "+albumName;
  }
}
