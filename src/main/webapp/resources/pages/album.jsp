<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src=${currentAlbum.imagePath}>
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">Album</span>
      </div>
      <div class="row">
        <a href="#">
          <h3 class="mediaName">${currentAlbum.albumName}</h3>    
        </a>
      </div>
      <div class="row">
        <a href="#" onclick="viewArtist(${currentAlbum.artistId.artistId})">By ${currentAlbum.artistId.artistName}</a>
      </div>
      <div class="row">
        <div class="dropdown">
          <button class="btn btn-primary" id="playlistPlayButton" onclick="playSong(${albumSongs[0].songId},'album',0)">Play</button>
          <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Options
            <span class="fa fa-chevron-circle-down"></span>
          </button>
          <c:choose>
            <c:when test = "${currentUser.isFollowingAlbum(currentAlbum)}">
              <ul class="dropdown-menu">
                <li><a href="#" onclick="unfollowAlbum(${currentAlbum.albumId},'album.jsp')">Unfollow</a></li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="followAlbum(${currentAlbum.albumId},'album.jsp')">Follow</a></li>
              </ul> 
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table songTable">
    <tr>
      <th class="col-md-2">#</th>
      <th class="col-md-8">Title</th>
      <th class="col-md-2 durationColumn">Duration</th>
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
            <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>
            :
            <fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>
