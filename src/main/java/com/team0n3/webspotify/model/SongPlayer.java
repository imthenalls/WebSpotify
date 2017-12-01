
package com.team0n3.webspotify.model;

import java.util.ArrayList;
import java.util.AbstractList;
import java.util.List;
public class SongPlayer {
    //album in it, or playlist
    //current song
    private int currentSongId;
    private Album currentAlbum;
    private Playlist currentPlaylist;
    private int currentPlaylistPos;
   // private CircularArrayList queue = new CircularArrayList():
    public SongPlayer(){}
    
    public SongPlayer( Playlist currentPlaylist, int currentSongId){
        this.currentPlaylist = currentPlaylist;
        this.currentSongId = currentSongId;
        currentPlaylistPos = 0;
    }
    
    public void getNextSong(){
        Playlist playlist = currentPlaylist;
        List<Song> playlistSongs = new ArrayList( playlist.getSongs());
        System.out.println("current pos "+currentPlaylistPos);
        try{
            currentPlaylistPos++;
            int nextSongId = playlistSongs.get(currentPlaylistPos).getSongId();   
            setCurrentSongId(nextSongId);
            System.out.println("current pos "+currentPlaylistPos);
        }catch(IndexOutOfBoundsException e){
            currentPlaylistPos--;
            System.out.println("current pos in catch last song"+currentPlaylistPos);
            setCurrentSongId(currentSongId);
        }
        //should return a song
        
    }

    public void getPrevSong(){
        Playlist playlist = currentPlaylist;
        List<Song> playlistSongs = new ArrayList( playlist.getSongs());
        System.out.println("current pos "+currentPlaylistPos);
        try{
            currentPlaylistPos--;
            int nextSongId = playlistSongs.get(currentPlaylistPos).getSongId();   
            setCurrentSongId(nextSongId);
            System.out.println("current pos "+currentPlaylistPos);
        }catch(IndexOutOfBoundsException e){
            currentPlaylistPos++;
            System.out.println("current pos in catch first song"+currentPlaylistPos);
            setCurrentSongId(currentSongId);
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
}
