<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Search for Song: ${lastSearch}</h2>   
    </div>
  </div>
</div>
<div class="row tableContainer" id="table">
  <table class="table songTable">
      <th class="col-md-l">#</th>
      <th class="col-md-4">Title</th>
      <th class="col-md-2">Artist</th>
      <th class="col-md-2">Album</th> 
      <th class="col-md-1 text-right">Options</th>
      <th class="col-md-1 text-right">Duration</th>
      <th class="col-md-1 text-right">Play Count</th>
      <c:forEach varStatus="loop" items="${allSongs}" var="Song">
        <tr class="tableRow">
          <td>
              <a class="playHide">
                  ${loop.index+1}
              </a>        
            <a href="#" onclick="playSong(${Song.songId},'artist',${loop.index})">
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
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="unfollowSong">Unfollow Song</a></li>
                  </c:when>
                  <c:otherwise>
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="followSong">Follow Song</a></li>
                  </c:otherwise>
                </c:choose>
                <c:if test="${currentUser.accountType == 'Admin'}">
                 <li><a href="#" onclick=adminRemoveSong(${Song.songId})>Remove From songs</a></li>
                </c:if>
                <c:if test="${currentUser.accountType == 'Artist'}">
                  <li><a href="#" onclick=artistRequestSongRemoval(${Song.songId},'searchSongs.jsp')>Request remove song</a></li>
                </c:if>
                <li class="divider">
                <li class="dropdown-submenu">
                  <a href="#">Add to Playlist</a>
                </li>
                <ul class="dropdown-menu">
                  <c:forEach items="${createdPlaylists}" var="Playlist">
                    <li><a href="#" onclick="addSongToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
                  </c:forEach>
                </ul>
              </ul>
            </div>
          </td>
          <td class="text-right">
            <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
          </td>
          <td class='text-right'>
            ${Song.totalPlays}
          </td>
        </c:forEach>
  </table>
</div>
