/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;
import java.util.List;
import com.team0n3.webspotify.dao.VenueDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.Venue;
import org.hibernate.Query;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author JSCHA
 */
public class VenueDAOHibernateImpl implements VenueDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public VenueDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addVenue(Venue venue) {
        sessionFactory.getCurrentSession().persist(venue);
    }
    

    @Override
    public Venue getVenue(int id) {
        return (Venue)sessionFactory.getCurrentSession().load(Venue.class, id);
    }

    @Override
    public List<Venue> listVenues(){
        List<Venue> venueList = sessionFactory.getCurrentSession().createCriteria(Venue.class).list();
        ListIterator<Venue> litr = null;
        litr=venueList.listIterator();
         while(litr.hasNext()){
            System.out.println(litr.next().toString());
        }
        return venueList;
    }

    @Override
    public void updateVenue(Venue venue){
        sessionFactory.getCurrentSession().update(venue);
    }

    @Override
    public void deleteVenue(Venue venue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
