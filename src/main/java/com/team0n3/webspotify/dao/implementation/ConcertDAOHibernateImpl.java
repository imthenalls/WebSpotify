/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao.implementation;
import java.util.List;
import com.team0n3.webspotify.dao.ConcertDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.Concert;
import org.hibernate.Query;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author JSCHA
 */
public class ConcertDAOHibernateImpl implements ConcertDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public ConcertDAOHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addConcert(Concert concert) {
        sessionFactory.getCurrentSession().persist(concert);
    }
    

    @Override
    public Concert getConcert(int id) {
        return (Concert)sessionFactory.getCurrentSession().load(Concert.class, id);
    }

    @Override
    public List<Concert> listConcerts(){
        List<Concert> concertList = sessionFactory.getCurrentSession().createCriteria(Concert.class).list();
        ListIterator<Concert> litr = null;
        litr=concertList.listIterator();
         while(litr.hasNext()){
            System.out.println(litr.next().toString());
        }
        return concertList;
    }

    @Override
    public void updateConcert(Concert concert){
        sessionFactory.getCurrentSession().update(concert);
    }

    @Override
    public void deleteConcert(Concert concert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
