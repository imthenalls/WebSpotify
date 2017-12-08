<div class="container" id="browseDiscover">
  <div class="row">
    <h3>Artists not in your Library</h3>
    <c:forEach items="${discoverArtists}" var="discoverArtist">
      <div class="col-sm-3" style="height:380px;">
          <div class="thumbnail">
              <a href="#x"><img src="${discoverArtist.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${discoverArtist.artistName}</h4></a>
          </div>
      </div>
    </c:forEach>
  </div>
  <div class="row">
    <h3>Albums not in your Library</h3>
    <c:forEach items="${discoverAlbums}" var="discoverAlbum">
      <div class="col-sm-3" style="height:380px;">
          <div class="thumbnail">
              <a href="#x"><img src="${discoverAlbum.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${discoverAlbum.albumName}</h4></a>
          </div>
      </div>      
    </c:forEach>
  </div>
</div>