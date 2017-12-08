<div class="container" id="browseNew">
  <div class="row">
    <h3>New Artists</h3>
    <c:forEach items="${newArtists}" begin="0" end="3" var="newArtist">
      <div class="col-sm-3">
          <div class="thumbnail">
              <a href="#x"><img src="${newArtist.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${newArtist.artistName}</h4></a>
          </div>
      </div>
    </c:forEach>
  </div>
  <div class="row">
    <h3>New Albums</h3>
    <c:forEach items="${newAlbums}" begin="0" end="3" var="newAlbum">
      <div class="col-sm-3">
          <div class="thumbnail">
              <a href="#x"><img src="${newAlbum.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${newAlbum.albumName}</h4></a>
          </div>
      </div>      
    </c:forEach>
  </div>
</div>