<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Search for Song: ${lastSearch}</h2>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div class="row" id="table">
  <table class="table songTable">
      <c:forEach varStatus="loop" items="${allSongs}" var="Song">
        <tr class="tableRow">
          <td class="col-md-2">
              <a class="playHide">
                  ${loop.index+1}
              </a>        
            <a href="#" onclick="playSong(${Song.songId},'artist',${loop.index})">
              <i class="playShow fa fa-play fa-fw"></i>
            </a>
          </td>
          <td class='col-md-4'><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
          <td class="col-md-1 text-right">
            <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
          </td>
          <td class="text-right col-md-1">
            <div class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="fa fa-ellipsis-h songOptions" id="dropdownMenu1"></i>
              </a>
              <ul class="dropdown-menu">
                <c:choose>
                  <c:when test="${currentUser.isFollowingSong(Song)}">
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="unfollowSong">Unfollow Song</a></li>
                  </c:when>
                  <c:when test="${currentUser.accountType == 'Admin'}">
                   <li><a href="#" onclick=adminRemoveSong(${Song.songId})>Remove From songs</a></li>
                  </c:when>
                  <c:when test="${currentUser.accountType == 'Artist'}">
                    <li><a href="#" onclick=artistRequestSongRemoval(${Song.songId},'searchSongs.jsp')>Request remove song</a></li>
                  </c:when>
                  <c:otherwise>
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="followSong">Follow Song</a></li>
                  </c:otherwise>
                </c:choose>
                <c:forEach items="${createdPlaylists}" var="Playlist">
                  <li><a href="#" onclick="addSongToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
                </c:forEach>
              </ul>
            </div>
          </td>
          <td class='col-md-1 text-right'>
            ${Song.totalPlays}
          </td>
        </c:forEach>
      </table>
</div>
