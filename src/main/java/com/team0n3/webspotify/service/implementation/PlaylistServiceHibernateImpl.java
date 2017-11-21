
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.SongService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PlaylistServiceHibernateImpl implements PlaylistService{
    
    @Autowired
    private PlaylistDAO playlistDao;
    @Autowired
    private SongService songService;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly=false)
    public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser) {
        Playlist playlist = new Playlist(playlistName,imagePath,description,currentUser);
        playlistDao.addPlaylist(playlist);
        return playlist;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Playlist> listAllPlaylists(){
        return playlistDao.listPlaylists();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Playlist getPlaylistByID(int playlistID){
        return playlistDao.getPlaylist(playlistID);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Song> getSongsInPlaylists(int playlistId){
        Playlist playlist = playlistDao.getPlaylist(playlistId);
        if(playlist == null)
            return null;
        Collection<Song> sondsInPlaylist = playlist.getSongs();
        List<Song> songList = new ArrayList(sondsInPlaylist);
        return songList;
    }
    
    @Override
    @Transactional(readOnly=false)
    public Playlist AddSongToPlaylist(int songId, int playlistId){
        Playlist playlist = getPlaylistByID(playlistId);
        Song song = songService.getSong(songId);
        Collection<Song> songsInPlaylist = playlist.getSongs();
        songsInPlaylist.add(song);
        playlist.setSongs(songsInPlaylist);
        playlistDao.updatePlaylist(playlist);
        return playlist;
        
    }
}
