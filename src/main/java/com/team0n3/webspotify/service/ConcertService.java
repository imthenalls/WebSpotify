/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;
import com.team0n3.webspotify.model.Concert;
import java.util.List;

public interface ConcertService {
    public Concert getConcert(int id);
    public void addNewConcert(String concertName, String address); 
    public List<Concert> listAllConcerts();
}
