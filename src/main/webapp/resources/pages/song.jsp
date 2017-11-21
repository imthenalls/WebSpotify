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
          <h3 class="mediaName">Songs</h3>    
        </a>
      </div>
      <div class="row" id="mediaSpecs">
        <span>
          ${currentPlaylist.description}
        </span>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table songTable">
    <tr>
      <th>Title</th>
      <th>Artist</th>
      <th>Album</th> 
      <th>Duration</th>
    </tr>
    <c:forEach items="${songList}" var="Song">
      <tr>
        <td>${Song.title}</a></td>
        <td>Artist</td>
        <td><a href="#" id="album${Song.albumId.albumId}" onclick="viewAlbum(this.id)">${Song.albumId.albumName}</a></td>
        <td>Duration</td>
        <td>
          <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
              Add to
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <c:forEach items="${PlaylistList}" var="Play">
                <li><a href="#" id="pl${play.playlistName}" onclick="addToPlaylist(${Play.playlistID}, ${Song.songId})">${Play.playlistName}</a></li>
              </c:forEach>
            </ul>
          </div>
        </td>

      </tr>
    </c:forEach> 
  </table>
</div>