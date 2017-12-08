<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Top Playlists</h1>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div>
  <div class="container-fluid">
    <c:forEach items="${topPlaylists}" varStatus='loop' var="Playlist">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewPlaylist(${Playlist.playlistID})"><img src="${Playlist.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowPlaylist(${Playlist.playlistID},'topPlaylists.jsp')"></button>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <li><a href="#" onclick="unfollowPlaylist(${currentPlaylist.playlistID},'topPlaylists.jsp')">Unfollow</a></li>
              <li><a href='#' id="addPlaylistToQueue" playlist="${currentPlaylst.playlistID}">Add to Queue</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewPlaylist(${Playlist.playlistID})"><h4>${Playlist.playlistName}</h4></a>
      </div>
    </c:forEach>
    </div>
  </div>