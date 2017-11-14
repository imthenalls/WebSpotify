/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.Concert;
/**
 *
 * @author JSCHA
 */
public interface ConcertDAO {
    public void addConcert(Concert concert);
    
    public Concert getConcert(int id);
    
    public List<Concert> listConcerts();
     
    public void updateConcert(Concert concert);
     
    public void deleteConcert(Concert concert);
}
