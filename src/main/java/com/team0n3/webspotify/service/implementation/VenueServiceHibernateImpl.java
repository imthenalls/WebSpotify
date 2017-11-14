/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.VenueDAO;
import com.team0n3.webspotify.model.Venue;
import com.team0n3.webspotify.service.VenueService;
import java.util.Arrays;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class VenueServiceHibernateImpl implements VenueService{
    @Autowired
    private VenueDAO venueDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Venue getVenue(int id) {
        Venue venue = venueDao.getVenue(id);
        if(venue == null)
            return null;
        return venue;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewVenue(String venueName, String address) {
        Venue venue = new Venue(venueName, address);
        venueDao.addVenue(venue);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Venue> listAllVenues()
    {
        List<Venue> listVenues = venueDao.listVenues();
        
        return listVenues;
    }
    
}
