<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="${currentArtist.imagePath}" height="100" width="100">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">Artist</span>
      </div>
      <div class="row">
          <h2 id="artistHeader" class="mediaName">${currentArtist.artistName}</h2>    
      </div>
      <div id="artistBio">
          <p id="summary"></p>
      </div>
    </div>
  </div>
  <div class="col-md-12">
    <button class="btn btn-primary" id="artistPlayButton">Play</button>
    <c:choose>
      <c:when test="${currentUser.isFollowingArtist(currentArtist)}">
        <button class="btn btn-info unfollowArtist" artistId="${currentArtist.artistId}" currentPage="artist.jsp">Unfollow</button>
      </c:when>
      <c:otherwise>
        <button class="btn btn-info followArtist" artistId="${currentArtist.artistId}" currentPage='artist.jsp'>Follow</button>
      </c:otherwise>
    </c:choose>
  </div> 
</div>

<div class="row">
  <div class="col-md-8">
      <h3>Popular</h3>
      <table class="table songTable">
      <c:forEach begin="0" end="9" varStatus="loop" items="${popularSongs}" var="Song">
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
  <div class="col-md-4">
    
  </div> 
</div>

<div>
  <h3>Albums</h3>
  <div class="container-fluid">
    <c:forEach items="${artistAlbums}" var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" alt="Image" class="img-responsive albumPic"></a>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
      
<div id="artistContentModal" class="modal fade" role="dialog">
   <div class="modal-dialog" role="document">  
    <div class="modal-content"> 
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="artistContent" class="modal-body">
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/artist_bio.js"></script>
