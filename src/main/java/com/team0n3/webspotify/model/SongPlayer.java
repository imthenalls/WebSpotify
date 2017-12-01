
package com.team0n3.webspotify.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class SongPlayer {
    //album in it, or playlist
    //current song
    private int currentSongId;
    
    private Album currentAlbum;
    private Playlist currentPlaylist;
    
    private int position;
    
    //get next song returns next song in queue
    //spotify controller sets that as current song
    //javascript reloads bottom toolbar with new information
    //on succes, plays current song
    
    private LinkedList queue = new LinkedList<Song>();
    private Queue shuffledQueue = new LinkedList<Song>();
    
    private boolean isRepeatSet;
    private boolean isRepeatSong;
    
    private int tailIndex;
    
    public SongPlayer(){
        
    }
    
    public SongPlayer( Playlist currentPlaylist, int currentSongId){
        this.currentPlaylist = currentPlaylist;
        this.currentSongId = currentSongId;
        position = 0;
    }

    public Song getNextSong(){
      if(isRepeatSong) //if repeat is toggled, send back the same song
        return (Song)queue.get(position);
      
      if(position == tailIndex){ //currently at last element of queue
        if(isRepeatSet){
          position = 0;
          return (Song)queue.get(position);
        }
        else
          return null;
      }
      else{ //simply moving to next song in queue
        position++;
        return (Song)queue.get(position);
      }
    }

    public Song getPrevSong(){
      if(position == 0){ //if current song is first in set
        if(isRepeatSet){ //if repeat is enabled, go to last song
          position = tailIndex;
          return (Song)queue.get(position);
        }
        else{
          return (Song)queue.get(position); //restart song
        }
      }
      else{ //simply moving to prev song
        position--;
        return (Song)queue.get(position);
      }
    }
    
    public int getCurrentSongId() {
        return currentSongId;
    }

    public void setCurrentSongId(int currentSongId) {
        this.currentSongId = currentSongId;
    }

    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(Album currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }
   
    public Queue getQueue(){
      return queue;
    }
    
    public void setQueue(Collection<Song> songs,int songIndex){
     queue.clear();
     queue.addAll(songs);
     tailIndex = queue.size()-1;
     position = songIndex;
    }
}
