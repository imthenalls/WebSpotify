/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.UserService;
import java.util.Arrays;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 *
 * @author JSCHA
 */
@Service
@Transactional(readOnly = true)
public class UserServiceHibernateImpl implements UserService{
    private final static Logger LOGGER = Logger.getLogger("UserService");
    @Autowired
    private UserDAO userDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User login(String username, String password) {
        User user= userDao.getUser(username);
        if(user==null){
            return null;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceHibernateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(user.getSalt());
        md.update(password.getBytes());
        byte hashedPass[] = md.digest();
        if(!Arrays.equals(user.getPassword(), hashedPass)){
            return null;
        }
        return user;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void signup(String username, String password, String email) {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[12];
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceHibernateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        random.nextBytes(salt);
        md.update(salt);
        md.update(password.getBytes());
        byte hashedPass[]=md.digest();
        User user= new User(username, email, hashedPass, salt);
        userDao.addUser(user);
    }
    
}
