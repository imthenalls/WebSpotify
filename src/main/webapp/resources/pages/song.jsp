<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Songs</h2>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
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
      <th></th>
    </tr>
    <c:forEach items="${songList}" var="Song">
      <tr>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
        <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
        <td>${Song.duration}</td>
        <td>
          <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
              Add to
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <c:forEach items="${PlaylistList}" var="Playlist">
                <li><a href="#" onclick="addToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
              </c:forEach>
            </ul>
          </div>
        </td>

      </tr>
    </c:forEach> 
  </table>
</div>