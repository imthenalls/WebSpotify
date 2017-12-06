/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator;

import com.team0n3.webspotify.model.Song;
import java.util.Comparator;

/**
 *
 * @author spike
 */
public class SongComparator implements Comparator<Song> {
  @Override
  public int compare(Song a,Song b){
    return b.getTotalPlays()-a.getTotalPlays();
  }
}