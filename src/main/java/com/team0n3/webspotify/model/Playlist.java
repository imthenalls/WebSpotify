
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="playlists")
public class Playlist implements Serializable{

  @Id
  @Column(name="playlistID",nullable=false)
  @GeneratedValue
  private int playlistID;
  
  @Column(name="playlistName",nullable=false)
  private String playlistName;
  
  @Column(name="songCount",nullable=false)
  private int songCount;
  
  @Column(name="isPublic",nullable=false)
  private boolean isPublic;
  
  @Column(name="numFollowers",nullable=false)
  private int numFollowers;
  
  @Column(name="dateCreated",nullable=false)
  private Date dateCreated;
  
  @Column(name="isCollaborative",nullable=false)
  private boolean isCollaborative;
  
  @Column(name="description",nullable=false)
  private String description;

  @ManyToOne
  @JoinColumn(name="creator",referencedColumnName="username",nullable=false)
  private User creator;

  @ManyToMany(mappedBy="followedPlaylists")
  private Collection<User> followers;

  @ManyToMany(mappedBy="collabPlaylists")
  private Collection<User> collaborators;

  @Column(name="imagePath")
  private String imagePath;

  @ManyToMany(mappedBy="containedInPlaylists")
  private Collection<Song> songs;

  public Playlist(){}

  public Playlist(String playlistName, String imagePath, String description, User creator){
    this.playlistName=playlistName;
    songCount=0;
    isPublic=false;
    numFollowers=1;
    dateCreated=new Date();
    isCollaborative=false;
   // songs = null;
    this.description=description;
    this.creator = creator;
    this.imagePath = imagePath;
  }

  public int getPlaylistID() {
    return playlistID;
  }

  public void setPlaylistID(int playlistID) {
    this.playlistID = playlistID;
  }

  public Collection<Song> getSongs() {
    return songs;
  }

  public void setSongs(Collection<Song> songs) {
    this.songs = songs;
  }
  public String getPlaylistName() {
    return playlistName;
  }

  public void setPlaylistName(String playlistName) {
    this.playlistName = playlistName;
  }

  public int getSongCount() {
    return songCount;
  }

  public void setSongCount(int songCount) {
    this.songCount = songCount;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public int getNumFollowers() {
    return numFollowers;
  }

  public void setNumFollowers(int numFollowers) {
    this.numFollowers = numFollowers;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public boolean getIsCollaborative() {
    return isCollaborative;
  }

  public void setIsCollaborative(boolean isCollaborative) {
    this.isCollaborative = isCollaborative;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public Collection<User> getCollaborators() {
    return collaborators;
  }

  public void setCollaborators(Collection<User> collaborators) {
    this.collaborators = collaborators;
  }

  public Collection<User> getFollowers() {
    return followers;
  }

  public void setFollowers(Collection<User> followers) {
    this.followers = followers;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
  
  /**
  public boolean hasFollower(User user){
    List<User> followersAsList = new ArrayList();
    followersAsList.addAll(followers);
    for (User u : followersAsList) {
      if(u.getUsername().equals(user.getUsername()))
        return true;
    }
    return false;
  }
  **/
  
  @Override
  public String toString(){
    return "Playlist = " + playlistID;
  }
}
