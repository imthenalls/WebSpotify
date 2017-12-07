<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Queue</h2>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div class="row tableContainer" id="queue">
  <table class="table songTable">
    <tr>
      <th class="col-md-l">#</th>
      <th class="col-md-3">Title</th>
      <th class="col-md-2">Artist</th>
      <th class="col-md-2">Album</th> 
      <th class="col-md-2 text-right">Options</th>
      <th class="col-md-2 text-right">Duration</th>
    </tr>
    <c:forEach items="${queueSongs}" varStatus="loop" var="Song">
      <tr class="tableRow">
        <td>
          <a class="playHide">
            ${loop.index+1}
          </a>        
          <a href="#" onclick="playSong(${Song.songId},'queue',${loop.index})">
            <i class="playShow fa fa-play fa-fw"></i>
          </a>
        </td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
        <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
        <td class="text-right">
          <div class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-ellipsis-h songOptions" id="dropdownMenu1"></i>
            </a>
            <ul class="dropdown-menu multi-level">
              <c:choose>
                <c:when test="${currentUser.isFollowingSong(Song)}">
                  <li><a href="#" songId="${Song.songId}" currentPage="queue.jsp" class="unfollowSong">Unfollow Song</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="#" songId="${Song.songId}" currentPage="queue.jsp" class="followSong">Follow Song</a></li>
                </c:otherwise>
              </c:choose>
                <li class="divider"></li>
                <li class="dropdown-submenu">
                  <a href="#">Add to Playlist</a>
                  <ul class="dropdown-menu">
                    <c:forEach items="${createdPlaylists}" var="Playlist">
                      <li><a href="#" onclick="addSongToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
                    </c:forEach>
                  </ul>
                </li>
            </ul>
          </div>
        </td>
        <td class="text-right">            
          <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>