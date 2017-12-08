<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
                <a playlistID="${currentPlaylist.playlistID}" id="playlistID" style="display: none;">${currentPlaylist.playlistID}</a>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" onerror="src='http://placehold.it/250x250'" src=${currentPlaylist.imagePath}>
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
        <div class="dropdown">
          <button class="btn btn-primary" id="playlistPlayButton" onclick="playSong(${songList[0].songId},'playlist',0)">Play</button>
          <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Options
            <span class="fa fa-chevron-circle-down"></span>
          </button>
          <ul class="dropdown-menu">
          <c:choose>
            <c:when test = "${currentPlaylist.creator.username==currentUser.username}">
                <li><a href="#" onclick="deletePlaylist()">Delete</a></li>
                <li><a href="#editPlaylistModal" data-toggle="modal">Edit Playlist</a></li>
            <!-- Rounded switch -->
                <c:choose>
                  <c:when test="${currentPlaylist.isPublic}">
                    <li><a href="#makePrivate" id="makePrivate">Make Private</a></li>
                  </c:when>
                  <c:otherwise>
                    <li><a href="#makePublic" id="makePublic">Make Public</a></li>
                  </c:otherwise>
                </c:choose>
              </ul>
            </c:when>
            <c:when test = "${currentUser.isFollowingPlaylist(currentPlaylist)}">
                <li><a href="#" onclick="unfollowPlaylist(${currentPlaylist.playlistID})">Unfollow</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#" onclick="followPlaylist(${currentPlaylist.playlistID})">Follow</a></li>
            </c:otherwise>
          </c:choose>
          <li>
            <a href="#" class="addPlaylistToQueue" playlistId="${currentPlaylist.playlistID}">Add to Queue</a>
          </li>
        </ul>
        </div>
    </div>
  </div>
</div>
        
<div id="editPlaylistModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content" id="modalBackground">
      <div class="modal-header">
        <span id="closeSpan"><button id="closeButton" type="button" class="btn fa fa-close" data-dismiss="modal"></button></span>
        <h4 class="modal-title">Edit Playlist: ${currentPlaylist.playlistName}</h4>
      </div>
      <form id="updatePlaylistForm" enctype="multipart/form-data">
        <div class="modal-body">
          <div class="row form-group">
            <input id='pName2' class="form-control" type="text" name="playlistName2" placeholder="${currentPlaylist.playlistName}">
          </div>
          <div class="row">
            <div class="col-xs-6 form-group">
              <img height="250" width="250" id="playlist-image2" src="${currentPlaylist.imagePath}" alt="Image" class="row mediaPic">
              <input id='iPath2' name="imagePath2" size='20' class="row form-control" type="file" accept="image/*" style="display: none;" >
            </div>
            <div class="col-xs-6 form-group">
              <textarea placeholder='${currentPlaylist.description}' id='pDesc2' class="form-control" type="textArea" rows="4" columns="5" form="updatePlaylistForm" maxlength="280" name="description"></textarea>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button id="editPlaylist" class="btn btn-info" type="button" value="Submit">Update</button>
        </div>
      </form>
    </div>
  </div>
</div>        
        
<div class="row tableContainer">
  <table class="table songTable">
    <tr>
      <th class="col-md-2"></th>
      <th class="col-md-4">Title</th>
      <th class="col-md-2">Artist</th>
      <th class="col-md-2">Album</th> 
      <th class="col-md-2 text-right">Duration</th>
      <th class="col-md-2 text-right">Options</th>
    </tr>
    <c:forEach items="${songList}" varStatus='loop' var="Song">
      <tr class="tableRow">
        <td>
          <a class="playHide">
            
          </a>
          <a href="#" onclick="playSong(${Song.songId},'playlist',${loop.index})">
            <i class="playShow fa fa-play fa-fw"></i>
          </a>
        </td>
        <td>${Song.title}</td>
        <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
        <td class="text-right">            
          <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/></td>
        <td class="text-right">
          <div class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-ellipsis-h songOptions" id="dropdownMenu1"></i>
            </a>
            <ul class="dropdown-menu">
              <c:choose>
                <c:when test="${currentUser.isFollowingSong(Song)}">
                  <li><a href="#" songId="${Song.songId}" currentPage="playlist.jsp" class="unfollowSong">Unfollow Song</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="#" songId="${Song.songId}" currentPage="playlist.jsp" class="followSong">Follow Song</a></li>
                </c:otherwise>
              </c:choose>
              <li><a href="#" onclick="deleteSongFromPlaylist(${currentPlaylist.playlistID},${Song.songId})">Remove From Playlist</a></li>
            </ul>
          </div>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>
