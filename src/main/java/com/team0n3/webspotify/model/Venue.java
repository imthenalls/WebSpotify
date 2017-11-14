/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Template
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
@Table(name="venues")
public class Venue implements Serializable{
    
    @Id
    @Column(name="venueid", nullable=false)
    @GeneratedValue
    private int id;
    
    @Column(name="venuename", nullable=false)
    private String venueName;
    
    @Column(name="address", nullable=false)
    private String address;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="venueid")
    private Collection<Concert> concerts;
    
    public Venue() {
    }
    
    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = address;
    }
    
    public int getVenueId(){
        return id;
    }
    
    public String getVenueName(){
        return venueName;
    }
    
    public String getVenueAddress(){
        return address;
    }
    
    public void setVenueName(String venueName)
    {
        this.venueName = venueName;
    }
    
    public void setVenueAddress(String address)
    {
        this.address = address;
    }
    @Override
    public String toString(){
        return "Venue = "+venueName;
    }
}
