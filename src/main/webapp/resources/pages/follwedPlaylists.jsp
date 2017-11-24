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
  <table class="table playlistTable">
    <tr>
      <th>Name</th>
      <th>Creator</th>
    </tr>
    <c:forEach items="${allPlaylists}" var="Playlist">
      <tr>
        <td><a href="#" onclick=viewPlaylist(${Playlist.playlistID})>${Playlist.playlistName}</a></td>
        <td>artist</td>
        <td>
           <button type="button" onclick="followPlaylist(${Playlist.playlistID})" class="btn btn-primary btn-sm">Follow</button>
             
            </button>
        </td>
      </tr>
    </c:forEach> 
  </table>
</div>