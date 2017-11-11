/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    public Song() {
    }
    public Song(String title) {
       this.title = title;
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