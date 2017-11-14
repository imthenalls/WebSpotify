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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="songs")
public class Song implements Serializable {
    @Id
    @Column(name="songid",nullable=false)
    @GeneratedValue
    private int songId;
    
    @Column(name="title",nullable=false)
    private String title;
    
    @ManyToOne
    @JoinColumn(name="albumId",referencedColumnName="aid",nullable=true)
    private Album albumId;
    
    public Song() {
    }
    public Song(String title) {
       this.title = title;
       this.albumId = null;
    }
    public Album getAlbum() {
        return albumId;
    }

    public void setAlbum(Album albumId) {
        this.albumId = albumId;
    }
    
    public int getSongId() {
        return songId;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString(){
        return "id="+songId+", name="+title;
    }
}