<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="/resources/img/foo.jpg">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">User</span>
      </div>
      <div class="row">
        <a href="#">
          <h2 class="mediaName">${currentArtist.artistName}</h2>    
        </a>
      </div>
    </div>
  </div>
</div>
<div class="row">
  <div class="col-md-8">
      <h3>Popular</h3>
      <table class="table songTable">
      <c:forEach begin="0" end="10" items="${artistSongs}" var="Song">
        <tr>
            <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
          <td>Duration</td>
        </tr>
      </c:forEach> 
      </table>
  </div>
  <div class="col-md-4">
    
  </div> 
</div>

<div>
  <h3>Albums</h3>
  <c:forEach items="${artistAlbums}" var="Album">
    <div class="col-md-3">
       <a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
      <a href="#" onclick="viewAlbum(${Album.albumId})"><img src="http://placehold.it/350x350" alt="Image" class="img-responsive"></a>
      <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
    </div>
  </c:forEach>
</div>