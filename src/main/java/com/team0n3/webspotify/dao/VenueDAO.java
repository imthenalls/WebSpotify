/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.Venue;

public interface VenueDAO {
    public void addVenue(Venue venue);
    
    public Venue getVenue(int id);
    
    public List<Venue> listVenues();
     
    public void updateVenue(Venue venue);
     
    public void deleteVenue(Venue venue);
}
