/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @Column(name="username", nullable=false)
    private String username;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="password", nullable=false)
    private byte[] password;
    
    
    public User() {
    }

    public User(String username, String email, byte[] password) {
       this.username = username;
       this.email = email;
       this.password = password;
    }
    public String getUserName() {
        return this.username;
    }
    public void setUserName(String userName) {
        this.username = userName;
    }
    public String getEmail() {
        return this.email;
    } 
    public void setEmail(String email) {
        this.email = email;
    }
    public byte[] getPassword() {
        return this.password;
    }
    
    public void setHashedPassword(byte[] password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return "username="+username+", email="+email;
    }
}