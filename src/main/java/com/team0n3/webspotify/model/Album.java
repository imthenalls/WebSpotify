/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="albums")
public class Album implements Serializable{
    
    @Id
    @Column(name="aid", nullable=false)
    @GeneratedValue
    private int aid;
    
    @Column(name="albumname", nullable=false)
    private String albumName;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="albumId")
    private Collection<Song> songs;
    
    public Album() {
    }
    
    public Album(String albumName) {
        this.albumName = albumName;
        this.songs = null;
    }
    public int getAid()
    {
        return aid;
    }
    public void setAid()
    {
        this.aid = aid;
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
    
    @Override
    public String toString(){
        return "Album = "+albumName;
    }
}
