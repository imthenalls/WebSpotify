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
            <c:when test = "${currentPlaylist.hasFollower(currentUser)}">
              <ul class="dropdown-menu">
                <li><a href="#" onclick="unfollowPlaylist(${currentPlaylist.playlistID})">Unfollow</a></li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="followPlaylist(${currentPlaylist.playlistID})">Follow</a></li>
              </ul> 
            </c:otherwise>
          </c:choose>
        
      
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
<div contextmenu="myMenu">Right-Click Me</div>
  <table class="table songTable">
    <tr>
      <th></th>
      <th>Title</th>
      <th>Artist</th>
      <th>Album</th> 
      <th>Duration</th>
      <th></th>
    </tr>
    <c:forEach items="${songList}" var="Song">
      <tr class="tableRow">
        <td>
          <a class="playHide">
            
          </a>
          <a href="#" onclick="playSong(${Song.songId})">
            <i class="playShow fa fa-play fa-fw"></i>
          </a>
        </td>
        <td>${Song.title}</td>
        <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
        <td>${Song.duration}</td>
        <td>
          <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
              Options
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#" onclick="deleteSongFromPlaylist(${currentPlaylist.playlistID},${Song.songId})">Remove From Playlist</a></li>
            </ul>
          </div>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>
