<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="/resources/img/foo.jpg">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">Playlist</span>
      </div>
      <div class="row">
        <a href="#">
          <h3 class="mediaName">${currentPlaylist.playlistName}</h3>    
        </a>
      </div>
      <div class="row">
        <a href="#">
          <span class="mediaCreator">${currentPlaylist.creator.username}</span>    
        </a>
      </div>
      <div class="row" id="mediaSpecs">
        <span>
          ${currentPlaylist.description}
        </span>
      </div>
      <div class="row">
        <div class="dropdown">
          <button class="btn btn-primary" id="playlistPlayButton">Play</button>
          <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Options
            <span class="fa fa-chevron-circle-down"></span>
          </button>
          <c:choose>
            <c:when test = "${currentPlaylist.creator.username==currentUser.username}">
              <ul class="dropdown-menu">
                <li><a href="#" onclick="deletePlaylist()">Delete</a></li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="unfollowPlaylist(${currentPlaylist.playlistID})">Unfollow</a></li>
              </ul>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
<div contextmenu="myMenu">Right-Click Me</div>
  <table class="table songTable">
    <tr>
      <th>Title</th>
      <th>Artist</th>
      <th>Album</th> 
      <th>Duration</th>
    </tr>
    <c:forEach items="${songList}" var="Song">
      <tr>
        <td>${Song.title}</td>
        <td>Artist</td>
        <td><a href="#" id="album${Song.albumId.albumId}" onclick="viewAlbum(this.id)">${Song.albumId.albumName}</a></td>
        <td>Duration</td>
      </tr>
    </c:forEach> 
  </table>
</div>