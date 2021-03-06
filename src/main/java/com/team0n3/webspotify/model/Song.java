
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
@Table(name="songs")
public class Song implements Serializable {
    
    @Id
    @Column(name = "songid",nullable = false)
    @GeneratedValue
    private int songId;
    
    @Column(name = "title",nullable = false)
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "albumId",referencedColumnName = "albumId",nullable=false)
    private Album albumId;
    
    @ManyToOne
    @JoinColumn(name = "artistId",referencedColumnName = "artistId",nullable=false)
    private Artist artistId;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, 
        CascadeType.MERGE })
    @JoinTable(
            name="songplaylist",
            joinColumns= {@JoinColumn(name="songid")},
            inverseJoinColumns = {@JoinColumn(name="playlistID")}
    ) 
    private Collection<Playlist> containedInPlaylists;
    
    @Column(name="duration",nullable=false)
    private int duration;
    
    @Column(name="audioPath")
    private String audioPath;
    
    @Column(name = "unpayedPlays")
    private int unpayedPlays;
    
    @Column(name = "totalPlays")
    private int totalPlays;
    
    @Column(name = "numFollowers")
    private int numFollowers;
    
    @Column(name = "royaltyPerPlay")
    private int royaltyPerPlay;
    
    @Column(name = "remove")
    private boolean removeSong;
    
    @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE }, mappedBy = "followedSongs")
    private Collection<User> followers;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="songId")
    private Collection<RoyaltyPayment> royaltyPayments;
        
    public Song() {
    }
    
    public Song(String title) {
       this.title = title;
    }

    public int currentPayOut(){
      int payOut = 0;
      setRoyaltyPerPlay(artistId.getPayoutPerPlay());
      payOut = unpayedPlays*royaltyPerPlay;
      return payOut;
    }
    
    public boolean isRemoveSong() {
      return removeSong;
    }

    public void setRemoveSong(boolean removeSong) {
      this.removeSong = removeSong;
    }
    
    public Collection<RoyaltyPayment> getRoyaltyPayments() {
      return royaltyPayments;
    }

    public void setRoyaltyPayments(Collection<RoyaltyPayment> royaltyPayments) {
      this.royaltyPayments = royaltyPayments;
    }
 
    public int getUnpayedPlays() {
      return unpayedPlays;
    }

    public void setUnpayedPlays(int unpayedPlays) {
      this.unpayedPlays = unpayedPlays;
    }
    
    public int getRoyaltyPerPlay() {
        return royaltyPerPlay;
    }

    public void setRoyaltyPerPlay(int royaltyPerPlay) {
        this.royaltyPerPlay = royaltyPerPlay;
    }
    
    public int getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(int totalPlays) {
        this.totalPlays = totalPlays;
    }

    public Collection<Playlist> getContainedInPlaylists(){
        return containedInPlaylists;
    }
    
    public void setContainedInPlaylists( Collection<Playlist> containedInPlaylists){
        this.containedInPlaylists = containedInPlaylists;
    }
    
    public int getSongId() {
        return songId;
    }
    
    public void setSongId(int songId) {
        this.songId = songId;
    }
    
    public Artist getArtistId() {
        return artistId;
    }
    
    public void setArtistId(Artist artistId) {
        this.artistId = artistId;
    }
    
    public Album getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(Album albumId) {
        this.albumId = albumId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public void setDuration(int duration){
        this.duration=duration;
    }
    
    public String getAudioPath(){
      return audioPath;
    }
    
    public void setAudioPath(String audioPath){
      this.audioPath=audioPath;
    }
    
    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }
    
    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }
    
    @Override
    public String toString(){
        return "id="+songId+", name="+title;
    }
}
