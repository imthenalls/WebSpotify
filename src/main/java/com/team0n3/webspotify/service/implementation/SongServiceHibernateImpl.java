
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.model.Playlist;
//import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.service.PlaylistService;
import com.team0n3.webspotify.service.SongService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

@Service
@Transactional(readOnly = true)
public class SongServiceHibernateImpl implements SongService{
    
    @Autowired
    private SongDAO songDao;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Song getSong(int songId) {
        Song song = songDao.getSong(songId);
        if(song == null)
            return null;
        return song;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewSong(String title) {
        Song song = new Song(title);
        songDao.addSong(song);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Song> listAllSongs()
    {
        List<Song> listSongs = songDao.listSongs();
        return listSongs;
    }
    
    @Transactional(readOnly = false)
    @Override
    public Song AddPlaylistToSong(int songId, int playlistId) {
       Song song = getSong(songId);
       Playlist playlist = playlistService.getPlaylistByID(playlistId);
       Collection<Playlist> contains = song.getContainedInPlaylists();
       contains.add(playlist);
       song.setContainedInPlaylists(contains);
       songDao.updateSong(song);
       return song;
    }
}
