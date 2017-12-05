<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src=${currentAlbum.imagePath}>
    </div>
    <div id="mediaInfo" class="col-xs-8">
        <h5 class="mediaType">Album</h5>
        <a href="#">
          <h3 class="mediaName">${currentAlbum.albumName}</h3>    
        </a>
        <a href="#" onclick="viewArtist(${currentAlbum.artistId.artistId})">By ${currentAlbum.artistId.artistName}</a>
     
      <div class="row">
        <div class="dropdown">
          <button class="btn btn-primary" id="playlistPlayButton" onclick="playSong(${albumSongs[0].songId},'album',0)">Play</button>
          <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Options
            <span class="fa fa-chevron-circle-down"></span>
          </button>
          <ul class='dropdown-menu'>
            <c:choose>
              <c:when test = "${currentUser.isFollowingAlbum(currentAlbum)}">
                  <li><a href="#" onclick="unfollowAlbum(${currentAlbum.albumId},'album.jsp')">Unfollow</a></li>
              </c:when>
              <c:otherwise>
                  <li><a href="#" onclick="followAlbum(${currentAlbum.albumId},'album.jsp')">Follow</a></li>
              </c:otherwise>
            </c:choose>
              <li><a href='#' id="addAlbumToQueue" album="${currentAlbum.albumId}">Add to Queue - not done</a></li>
              <li><a href='#' class='viewArtist' artist='${currentAlbum.artistId.artistId}'>View Artist - not done</a></li>
              <li><a href='#'>Add to Playlist - not done</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table songTable">
    <tr>
      <th class="col-md-2">#</th>
      <th class="col-md-4">Title</th>
      <th class="col-md-4 durationColumn">Duration</th>\
      <th class="col-md-2 ">Options</th>
    </tr>
    <c:forEach items="${albumSongs}" varStatus="loop" var="Song">
      <tr class="tableRow">
        <td>
            <a class="playHide">
                ${loop.index+1}
            </a>        
          <a href="#" onclick="playSong(${Song.songId},'album',${loop.index})">
            <i class="playShow fa fa-play fa-fw"></i>
          </a>
        </td>
        <td>${Song.title}</td>
        <td class="durationColumn">
            <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
        </td>
        <td>
          <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
              <span class="caret"></span>
            </button>
              <c:choose>
            <c:when test = "${currentUser.isFollowingSong(Song)}">
              <ul class="dropdown-menu">
                <li><a href="#" onclick="unfollowSong(${Song.songId},'album.jsp')">Unfollow Song</a></li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="followSong(${Song.songId},'album.jsp')">Follow Song</a></li>
              </ul>
            </c:otherwise>
          </c:choose>
            
          </div>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>
