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
import org.apache.commons.codec.digest.DigestUtils;

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
        if(user==null || !Arrays.equals(user.getPassword(), DigestUtils.sha256(password))){
            LOGGER.info("invalid credentials");
            return null;
        }
        LOGGER.info("Successful login");
        return user;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void signup(String username, String password, String email) {
        User user= new User(username, email, DigestUtils.sha256(password));
        userDao.addUser(user);
    }
    
}
