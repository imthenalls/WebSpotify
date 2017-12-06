
package com.team0n3.webspotify.model;

import com.team0n3.webspotify.enums.AccountType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="users")
public class User implements Serializable{

  @Id
  @Column(name="username", nullable=false)
  private String username;

  @Column(name="email", nullable=false)
  private String email;

  @Column(name="password", nullable=false)
  private byte[] password;
  
  @Column(name="salt")
  private byte[] salt;

  @Column(name="imagePath")
  private String imagePath;
  
  @OneToMany(cascade=CascadeType.ALL,mappedBy="creator")
  private Collection<Playlist> createdPlaylists;

  @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE })
  @JoinTable(
    name="FollowPlaylist",
    joinColumns= {@JoinColumn(name="username")},
    inverseJoinColumns = {@JoinColumn(name="playlistID")}
  )
  private Collection<Playlist> followedPlaylists;

  @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE })
  @JoinTable(
    name="followartist",
    joinColumns= {@JoinColumn(name="username")},
    inverseJoinColumns = {@JoinColumn(name="artistId")}
  )
  private Collection<Artist> followedArtists;
  
  @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE })
  @JoinTable(
    name="followsong",
    joinColumns= {@JoinColumn(name="username")},
    inverseJoinColumns = {@JoinColumn(name="songId")}
  )
  private Collection<Song> followedSongs;
  
  @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE })
  @JoinTable(
    name="followalbum",
    joinColumns= {@JoinColumn(name="username")},
    inverseJoinColumns = {@JoinColumn(name="albumId")}
  )
  private Collection<Album> followedAlbums;
  
  @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE })
  @JoinTable(
    name="CollabPlaylist",
    joinColumns= {@JoinColumn(name="username")},
    inverseJoinColumns = {@JoinColumn(name="playlistID")}
  )
  private Collection<Playlist> collabPlaylists;
  
  @ManyToOne
  @JoinColumn(name="paymentId",referencedColumnName="paymentId")
  private PaymentInfo paymentInfo;
  
  @Column(name="accountType",nullable=false)
  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  public User() {
  }

  public User(String username, String email, byte[] password, byte[] salt) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.salt = salt;
    createdPlaylists=null;
    followedPlaylists=null;
    collabPlaylists=null;
    paymentInfo=null;
    accountType=AccountType.Unapproved;
  }

  public Collection<Song> getFollowedSongs() {
      return followedSongs;
  }

  public void setFollowedSongs(Collection<Song> followedSongs) {
      this.followedSongs = followedSongs;
  }

  public Collection<Album> getFollowedAlbums() {
      return followedAlbums;
  }

  public void setFollowedAlbums(Collection<Album> followedAlbums) {
      this.followedAlbums = followedAlbums;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getEmail() {
    return this.email;
  } 
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public byte[] getPassword() {
    return this.password;
  }

  public void setPassword(byte[] password) {
    this.password = password;
  }

  public byte[] getSalt() {
    return salt;
  }

  public void setSalt(byte[] salt) {
    this.salt = salt;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
  
  public Collection<Playlist> getCreatedPlaylists() {
    return createdPlaylists;
  }

  public void setCreatedPlaylists(Collection<Playlist> createdPlaylists) {
    this.createdPlaylists = createdPlaylists;
  }

  public Collection<Playlist> getFollowedPlaylists() {
    return followedPlaylists;
  }

  public void setFollowedPlaylists(Collection<Playlist> followedPlaylists) {
    this.followedPlaylists = followedPlaylists;
  }

  public Collection<Playlist> getCollabPlaylists() {
    return collabPlaylists;
  }

  public void setCollabPlaylists(Collection<Playlist> collabPlaylists) {
    this.collabPlaylists = collabPlaylists;
  }
  
  public PaymentInfo getPaymentInfo(){
    return paymentInfo;
  }
  
  public void setPaymentInfo(PaymentInfo paymentInfo){
    this.paymentInfo = paymentInfo;
  }
  
  public AccountType getAccountType(){
    return accountType;
  }
  
  public void setAccountType(AccountType accountType){
    this.accountType = accountType;
  }
  
  public Collection<Artist> getFollowedArtists() {
        return followedArtists;
  }
  
  public void setFollowedArtists(Collection<Artist> followedArtists) {
        this.followedArtists = followedArtists;
  }
  
  public boolean isFollowingAlbum(Album album){
    List<Album> followedAlbumsAsList = new ArrayList<>();
    followedAlbumsAsList.addAll(followedAlbums);
    for (Album a : followedAlbumsAsList) {
      if(a.getAlbumId() == album.getAlbumId())
        return true;
    }
    return false;
  }
  
  public boolean isFollowingPlaylist(Playlist playlist){
    List<Playlist> followedPList = new ArrayList<>();
    followedPList.addAll(followedPlaylists);
    for (Playlist p : followedPList) {
      if(p.getPlaylistID() == playlist.getPlaylistID())
        return true;
    }
    return false;
  }
  
  public boolean isFollowingSong(Song song){
    List<Song> followedSong = new ArrayList<>();
    followedSong.addAll(followedSongs);
    for (Song s : followedSong) {
      if(s.getSongId() == song.getSongId())
        return true;
    }
    return false;
  }
  
    public boolean isFollowingArtist(Artist artist){
    List<Artist> followedArtist = new ArrayList<>();
    followedArtist.addAll(followedArtists);
    for (Artist a : followedArtist) {
      if(a.getArtistId() == artist.getArtistId())
        return true;
    }
    return false;
  }
  
  @Override
  public String toString(){
    return "username="+username+", email="+email;
  }
}