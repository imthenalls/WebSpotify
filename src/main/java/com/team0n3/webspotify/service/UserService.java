/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import java.util.List;

/**
 *
 * @author JSCHA
 */
public interface UserService {
  public User login(String username, String password);
  
  public User signup(String username, String password, String email);
  
  public List<Playlist> getCreated(String username);
}
