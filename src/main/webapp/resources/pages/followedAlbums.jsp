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
  <table class="table albumTable">
    <tr>
      <th>Title</th>
      <th>Artist</th>
    </tr>
    <c:forEach items="${followAlbum}" var="Album">
      <tr>
        <td><a href="#" id="album${Album.albumId}" onclick="viewAlbum(this.id)">${Album.albumName}</a></td>
        <td>artist</td>
        
      </tr>
    </c:forEach> 
  </table>
</div>