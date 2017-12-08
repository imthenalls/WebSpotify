<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" id="browseDiscover">
  <div class="container-fluid">
    <h3>Discover Artists</h3>
    <c:forEach items="${discoverArtists}" begin="0" end="15" var="discoverArtist">
      <div class="col-sm-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewArtist(${discoverArtist.artistId})"><img src="${discoverArtist.imagePath}" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">
            <button id="discoverArt" class="fa fa-plus albumButton discovered " artistId="${discoverArtist.artistId}"></button>
          </div>
          <a href="#" onclick="viewArtist(${discoverArtist.artistId})"><h4>${discoverArtist.artistName}</h4></a>
        </div>
      </div>
    </c:forEach>
  </div>
          
  <div class="container-fluid">
    <h3>Discover Albums</h3>
    <c:forEach items="${discoverAlbums}" begin="0" end="15" var="discoverAlbum">
      <div class="col-sm-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${discoverAlbum.albumId})"><img src="${discoverAlbum.imagePath}" alt="Image" class="img-responsive albumPic"></a>             
          <a href="#" onclick="viewAlbum(${discoverAlbum.albumId})"><h4>${discoverAlbum.albumName}</h4></a>
          <a href="#" onclick="viewArtist(${discoverAlbum.artistId.artistId})"><h6>${discoverAlbum.artistId.artistName}</h6></a>
        </div>
      </div>
    </c:forEach>
  </div>
</div>