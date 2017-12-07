<div class="container" id="browseOverview">
  <div class="row">
    <h3>Charts</h3>
      <div class="col-sm-3">
        <div class="thumbnail">
            <a href="" onclick="viewTop50Songs()"><img src="/resources/img/charts/songIcon.jpg" alt="Image" class="img-responsive"></a>
            <a href="" onclick="viewTop50Songs()"><h4>Top Songs</h4></a>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="thumbnail">
            <a onclick="viewTopArtists()" href="#x"><img src="/resources/img/charts/artistIcon.jpg" alt="Image" class="img-responsive"></a>
            <a onclick="viewTopArtists()" href=""><h4>Top Artists</h4></a>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="thumbnail">
            <a onclick="viewTopAlbums()" href="#x"><img src="/resources/img/charts/albumIcon.png" alt="Image" class="img-responsive"></a>
            <a onclick="viewTopAlbums()" href=""><h4>Top Albums</h4></a>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="thumbnail">
            <a onclick="viewTopPlaylists()" href="#x"><img src="/resources/img/charts/playlistIcon.png" alt="Image" class="img-responsive"></a>
            <a onclick="viewTopPlaylists()" href=""><h4>Top Playlists</h4></a>
        </div>
      </div>
  </div>
  <div class="row">
    <h3>Genres</h3>
    <c:forEach items="${genrePlaylists}" begin="0" end="3" var="genrePlaylist">
      <a href="#">
          <div class="col-xs-3">
              <img src="resources/img/genres/rock.png" alt="Rock">
              <h3>${genrePlaylist.playlistName}</h3>
          </div>                            
      </a>
    </c:forEach>
  </div>
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
  <div class="row">
    <h3>Artists not in your Library</h3>
    <c:forEach items="${discoverArtists}" begin="0" end="3" var="discoverArtist">
      <div class="col-sm-3">
          <div class="thumbnail">
              <a href="#x"><img src="${discoverArtist.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${discoverArtist.artistName}</h4></a>
          </div>
      </div>
    </c:forEach>
    <h3>Albums not in your Library</h3>
    <c:forEach items="${discoverAlbums}" begin="0" end="3" var="discoverAlbum">
      <div class="col-sm-3">
          <div class="thumbnail">
              <a href="#x"><img src="${discoverAlbum.imagePath}" alt="Image" class="img-responsive"></a>
              <a href=""><h4>${discoverAlbum.albumName}</h4></a>
          </div>
      </div>      
    </c:forEach>
  </div>
</div>