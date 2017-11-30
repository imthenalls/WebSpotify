<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="${currentArtist.imagePath}" height="100" width="100">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">Artist</span>
      </div>
      <div class="row">
        <a href="#">
          <h2 id="artistHeader" class="mediaName">${currentArtist.artistName}</h2>    
        </a>
      </div>
      <div id="artistBio">
          <p id="summary"></p>
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
            <td class="durationColumn">${Song.duration}</td>
        </tr>
      </c:forEach> 
      </table>
  </div>
  <div class="col-md-4">
    
  </div> 
</div>

<div>
  <h3>Albums</h3>
  <div class="container-fluid">
    <c:forEach items="${artistAlbums}" var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a class="albumPic" href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" alt="Image" class="img-responsive"></a>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
        </div>
        </div>
    </c:forEach>
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/artist_bio.js"></script>