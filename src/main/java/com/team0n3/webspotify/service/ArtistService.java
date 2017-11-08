/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;
import com.team0n3.webspotify.model.Artist;

/**
 *
 * @author JSCHA
 */
public interface ArtistService {
    public Artist getNewArtist(String artistName);
    public void addNewArtist(String artistName); 
  
}
