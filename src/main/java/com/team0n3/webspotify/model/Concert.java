/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Template
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
@Table(name="concerts")
public class Concert implements Serializable{
    
    @Id
    @Column(name="concertid", nullable=false)
    @GeneratedValue
    private int id;
    
    @Column(name="concertname", nullable=false)
    private String concertName;
    
    @Column(name="address", nullable=false)
    private String address;
    
    @Column(name="venueid", nullable=true)
    private int venueId;
    
    
    public Concert() {
    }
    
    public Concert(String concertName, String address) {
        this.concertName = concertName;
        this.address = address;
    }
    
    public int getConcertId(){
        return id;
    }
    
    public String getConcertName(){
        return concertName;
    }
    
    public String getConcertAddress(){
        return address;
    }
    
    public int getConcertVenueId(){
        return venueId;
    }
    
    public void setConcertName(String concertName)
    {
        this.concertName = concertName;
    }
    
    public void setConcertAddress(String address)
    {
        this.address = address;
    }
    @Override
    public String toString(){
        return "Concert = "+concertName;
    }
}
