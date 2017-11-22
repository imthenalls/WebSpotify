
package com.team0n3.webspotify.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @Column(name="totalPlays", nullable=true)
    private int totalPlays;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="artistId")
    private Collection<Album> albums;

    
    
    public Artist() {
    }
    
    public Artist(String artistName) {
        this.artistName = artistName;
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

   
    public int getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(int totalPlays) {
        this.totalPlays = totalPlays;
    }


    @Override
    public String toString(){
        return "Artist = "+artistName;
    }
}
