/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="albums")
public class Album implements Serializable{
    
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue
    private int id;
    
    @Column(name="albumName", nullable=false)
    private String albumName;
    
    
    public Album() {
    }
    
    public Album(String albumName) {
        this.albumName = albumName;
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
