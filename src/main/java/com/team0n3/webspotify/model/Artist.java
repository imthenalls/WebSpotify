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
@Table(name="artist")
public class Artist implements Serializable{
    
    @Id
    @Column(name="artistName", nullable=false)
    private String artistName;

    public Artist() {
    }
    
    public Artist(String artistName) {
        this.artistName = artistName;
    }
    
    public String getArtistName() {
        return this.artistName;
    }
    
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    
    @Override
    public String toString(){
        return "Artist = "+artistName;
    }
}