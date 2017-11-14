/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.ConcertDAO;
import com.team0n3.webspotify.model.Concert;
import com.team0n3.webspotify.service.ConcertService;
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
public class ConcertServiceHibernateImpl implements ConcertService{
    @Autowired
    private ConcertDAO concertDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Concert getConcert(int id) {
        Concert concert = concertDao.getConcert(id);
        if(concert == null)
            return null;
        return concert;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewConcert(String concertName, String address) {
        Concert concert = new Concert(concertName, address);
        concertDao.addConcert(concert);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Concert> listAllConcerts()
    {
        List<Concert> listConcerts = concertDao.listConcerts();
        
        return listConcerts;
    }
    
}
