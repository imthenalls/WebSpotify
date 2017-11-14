/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;
import com.team0n3.webspotify.model.Venue;
import java.util.List;

public interface VenueService {
    public Venue getVenue(int id);
    public void addNewVenue(String venueName, String address); 
    public List<Venue> listAllVenues();
}
