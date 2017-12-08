<div id="browseNew">  
  <div class="container-fluid">
    <h3>New Artists</h3>
    <c:forEach items="${newArtists}" begin="0" end="15" var="newArtist">
      <div class="col-sm-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewArtist(${newArtist.artistId})"><img src="${newArtist.imagePath}" alt="Image" class="img-responsive albumPic"></a>
          <a href="#" onclick="viewArtist(${newArtist.artistId})"><h4>${newArtist.artistName}</h4></a> 
        </div>
      </div>
    </c:forEach>
  </div>
      
  <div class="container-fluid">
    <h3>New Albums</h3>
    <c:forEach items="${newAlbums}" begin="0" end="15" var="newAlbum">
      <div class="col-sm-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${newAlbum.albumId})"><img src="${newAlbum.imagePath}" alt="Image" class="img-responsive albumPic"></a>        
          <a href="#" onclick="viewAlbum(${newAlbum.albumId})"><h4>${newAlbum.albumName}</h4></a>
          <a href="#" onclick="viewArtist(${newAlbum.artistId.artistId})"><h6>${newAlbum.artistId.artistName}</h6>
        </div>
      </div>      
    </c:forEach>
  </div>
</div>