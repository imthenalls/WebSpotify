
package com.team0n3.webspotify.model;

import java.util.ArrayList;
import java.util.AbstractList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SongPlayer {
    private Song currentSong;
  
    public SongPlayer(){}
    
    private int position, shuffledPosition;
    
    private List<Song> queue = new ArrayList();
    private List<Song> shuffledQueue = new ArrayList();
    
    private boolean isRepeatSet=false;
    private boolean isRepeatSong=false;
    private boolean isShuffle=false;
    
    private int tailIndex;

    public Song getNextSong(){
      if(isShuffle){
        if(isRepeatSong){
          currentSong = shuffledQueue.get(shuffledPosition);
          return currentSong;
        }
        if(shuffledPosition == tailIndex){
          if(isRepeatSet){
            shuffledPosition=0;
            currentSong = shuffledQueue.get(shuffledPosition);
            return currentSong;
          }
          else
            return null;
        }
        else{
          shuffledPosition++;
          currentSong = shuffledQueue.get(shuffledPosition);
          return currentSong;
        }
      }
      else{
        if(isRepeatSong) //if repeat is toggled, send back the same song
          return (Song)queue.get(position); 
        if(position == tailIndex){ //currently at last element of queue
          if(isRepeatSet){
            position = 0;
            currentSong = queue.get(position);
            return currentSong;
          }
          else
            return null;
        }
        else{ //simply moving to next song in queue
          position++;
          currentSong = queue.get(position);
          return currentSong;
        } 
      }
    }

    public Song getPrevSong(){
      if(isShuffle){
        if(shuffledPosition == 0){
          if(isRepeatSet){
            shuffledPosition = tailIndex;
            currentSong = shuffledQueue.get(shuffledPosition);
            return currentSong;
          }
          else{
            currentSong = shuffledQueue.get(shuffledPosition);
            return currentSong;
          }
        }
        else{
          shuffledPosition--;
          currentSong = shuffledQueue.get(shuffledPosition);
          return currentSong;
        }
      }
      else{
        if(position == 0){ //if current song is first in set
          if(isRepeatSet){ //if repeat is enabled, go to last song
            position = tailIndex;
            currentSong = queue.get(position);
            return currentSong;
          }
          else{
            currentSong = queue.get(position);
            return currentSong; //restart song
          }
        }
        else{ //simply moving to prev song
          position--;
          currentSong = queue.get(position);
          return currentSong;
        }        
      }
    }
    
    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public List<Song> getCorrectQueue(){
      if(isShuffle){
        List<Song> returnList = new ArrayList<>(shuffledQueue.subList(shuffledPosition,shuffledQueue.size()-1));
        return returnList;
      }
      else{
        List<Song> returnList = new ArrayList<>(queue.subList(position,queue.size()));
        return returnList;
      }
        
    }
    
    public void setQueues(Collection<Song> songs,int songIndex){
      if(!queue.isEmpty()){
        queue.clear();
        shuffledQueue.clear(); 
      }
      queue.addAll(songs);
      tailIndex = queue.size()-1;
      position = songIndex;
     
      currentSong = queue.get(songIndex); //song that's about to be played
    
      shuffledQueue.addAll(songs);
      Collections.shuffle(shuffledQueue);
      System.out.println("Original shuffled queue" + shuffledQueue);
      if(isShuffle){
        int shift = shuffledQueue.indexOf(currentSong);
        Collections.rotate(shuffledQueue,-shift);
        System.out.println("Shifted shuffle" + shuffledQueue);
      }
      shuffledPosition=0;
    }

  public void toggleRepeat(String setting) {
    if(setting.equals("repeatOff")){
      isRepeatSet=false;
      isRepeatSong=false;
    }
    else if (setting.equals("repeatSong"))
      isRepeatSong=true;
    else //turning on repeat
      isRepeatSet=true;
  }
  
  public void toggleShuffle(){
    isShuffle = !isShuffle;
    
    if(isShuffle){ //turning shuffle on
      int shift = shuffledQueue.indexOf(currentSong);
      shuffledPosition = 0;
      System.out.println(currentSong.getTitle() + " " + shift);
      Collections.rotate(shuffledQueue,-shift);
      System.out.println("After toggle and shift" + shuffledQueue);
    }
    else{ //turning shuffle off
      position = queue.indexOf(currentSong); //update the position in the queue
      System.out.println("position" + position);
    }
  }
}
