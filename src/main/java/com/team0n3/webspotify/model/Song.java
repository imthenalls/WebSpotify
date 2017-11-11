/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="songs")
public class Song {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;
    
    @Column
    private String title;
    
    @ManyToOne
    @JoinColumn(name="albumId",referencedColumnName="id",nullable=true)
    private Collection<Album> album;
    
    public Song() {
    }
    public Song(String title) {
       this.title = title;
       this.album = null;
    }
    public Collection<Album> getAlbum() {
        return album;
    }

    public void setAlbum(Collection<Album> album) {
        this.album = album;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString(){
        return "id="+id+", name="+title;
    }
}