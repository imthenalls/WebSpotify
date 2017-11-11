/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.service.SongService;
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
public class SongServiceHibernateImpl implements SongService{
    private final static Logger LOGGER = Logger.getLogger("UserService");
    @Autowired
    private SongDAO songDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
}
