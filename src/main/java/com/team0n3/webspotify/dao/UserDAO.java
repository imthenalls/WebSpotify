/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.User;
/**
 *
 * @author JSCHA
 */
public interface UserDAO {
  public void addUser(User user);

  public User getUser(String username);

  public List<User> listUsers();

  public void updateUser(User user);

  public void deleteUser(String username);

  public User findByEmail(String email);
}
