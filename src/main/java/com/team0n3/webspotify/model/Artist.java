
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="artists")
public class Artist implements Serializable{
    
    @Id
    @Column(name="artistId", nullable=false)
    @GeneratedValue
    private int artistId;
    
    @Column(name="artistName", nullable=false)
    private String artistName;
    
    @Column(name="popularity", nullable=true)
    private int popularity;
    
    @Column(name="imagePath", nullable=true)
    private String imagePath; 
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="artistId")
    private Collection<Album> albums;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="artistId")
    private Collection<Song> songs;
    
    @ManyToMany(cascade ={CascadeType.PERSIST, 
        CascadeType.MERGE }, mappedBy = "followartist")
    private Collection<User> followers;
    
    @OneToOne
    @JoinColumn(name="username",referencedColumnName="username")
    private User username;
    
    @Column(name = "totalRoyalties",nullable=true)
    private int totalRoyalties;
    
    public Artist() {
    }
    
    public Artist(String artistName) {
        this.artistName = artistName;
    }
    
    public Artist(String artistName, int popularity, String imagePath) {
        this.artistName = artistName;
        this.popularity = popularity;
        this.imagePath = imagePath;
    }

    public Artist(String artistName, User username) {
        this.artistName = artistName;
        this.username = username;
    }
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
    
    public Collection<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }
    
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Collection<Song> getSongs() {
        return songs;
    }
    
    public void setSongs(Collection<Song> songs) {
        this.songs=songs;
    }

    public String getImagePath(){
        return this.imagePath;
    }
    
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }

    public int getTotalRoyalties() {
        return totalRoyalties;
    }

    public void setTotalRoyalties(int totalRoyalties) {
        this.totalRoyalties = totalRoyalties;
    }
    
    @Override
    public String toString(){
        return "Artist = "+artistName;
    }
}
