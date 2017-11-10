/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

/**
 *
 * @author JSCHA
 */

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.UserService;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SpotifyController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArtistService artistService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView handleRequest(HttpSession session) {
        ModelAndView model = new ModelAndView("redirect:/login");
        return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginUser(HttpSession session) {
        ModelAndView model;
        if(null == session.getAttribute("loggedIn")){
            model = new ModelAndView("login");
            return model;    
        }
        model=new ModelAndView("browse");
        return model;
    }
    
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user=userService.login(username, password);
        if(user==null){
            return new ModelAndView("redirect:/");
        }
        session.setAttribute("loggedIn", user);
        ModelAndView model= new ModelAndView("redirect:/browse");
        return model;   
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpSession session) {
        
        ModelAndView model;
        if(null == session.getAttribute("loggedIn")){
            model = new ModelAndView("signup");
            return model;    
        }
        model = new ModelAndView("browse");
        return model;
    }
    
    @RequestMapping(value = "/doSignup", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.signup(username, password, email);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    public ModelAndView browse(HttpSession session) {
        ModelAndView model = new ModelAndView("browse");
        return model;
    }
    @RequestMapping(value = "/artistPage", method = RequestMethod.GET)
    public ModelAndView artist(HttpSession session) {
        ModelAndView model;
        model=new ModelAndView("artistPage");
        return model;
    }

    @RequestMapping(value = "/doAddArtist", method = RequestMethod.POST)
    public ModelAndView doAddArtist(@RequestParam String artistName) {
        artistService.addNewArtist(artistName);
        return new ModelAndView("redirect:/");
    }
}
