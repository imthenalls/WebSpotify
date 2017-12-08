<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" id="browseOverview">
  <div class="row">
    <h3>Charts</h3>
      <div class="col-sm-3 albumCard">
        <div class="thumbnail">
            <a href="" onclick="viewTop50Songs()"><img src="/resources/img/charts/songIcon.jpg" alt="Image" class="img-responsive albumPic"></a>
            <a href="" onclick="viewTop50Songs()"><h4>Top Songs</h4></a>
        </div>
      </div>
      <div class="col-sm-3 albumCard">
        <div class="thumbnail">
            <a onclick="viewTopArtists()" href="#x"><img src="/resources/img/charts/artistIcon.jpg" alt="Image" class="img-responsive albumPic"></a>
            <a onclick="viewTopArtists()" href=""><h4>Top Artists</h4></a>
        </div>
      </div>
      <div class="col-sm-3 albumCard">
        <div class="thumbnail">
            <a onclick="viewTopAlbums()" href="#x"><img src="/resources/img/charts/albumIcon.png" alt="Image" class="img-responsive albumPic"></a>
            <a onclick="viewTopAlbums()" href=""><h4>Top Albums</h4></a>
        </div>
      </div>
      <div class="col-sm-3 albumCard">
        <div class="thumbnail">
            <a onclick="viewTopPlaylists()" href="#x"><img src="/resources/img/charts/playlistIcon.png" alt="Image" class="img-responsive albumPic"></a>
            <a onclick="viewTopPlaylists()" href=""><h4>Top Playlists</h4></a>
        </div>
      </div>
  </div>
  <div class="row">
    <h3>Genres</h3>
    <c:forEach items="${allGenres}" begin="0" end="3" var="genre">
      <div class="col-xs-3 albumCard">
        <a href="" onclick="viewGenrePlaylist(${genre})"><img src="resources/img/genres/rock.png" alt="Rock"></a>
        <a href="" onclick="viewGenrePlaylist(${genre})"><h3>${genre}</h3></a>
      </div>                            
    </c:forEach>
  </div>
          
  <div class="container-fluid">
    <h3>New Artists</h3>
    <c:forEach items="${newArtists}" begin="0" end="3" var="newArtist">
      <div class="col-sm-3 albumCard">
        <a href="" onclick="viewArtist({newArtist.artistId})"><img src="${newArtist.imagePath}" alt="Image" class="img-responsive albumPic"></a>
        <a href="" onclick="viewArtist({newArtist.artistId})"><h4>${newArtist.artistName}</h4></a> 
      </div>
    </c:forEach>
  </div>
      
  <div class="container-fluid">
    <h3>New Albums</h3>
    <c:forEach items="${newAlbums}" begin="0" end="3" var="newAlbum">
      <div class="col-sm-3 albumCard">
        <a href="" onclick="viewAlbum({newAlbum.artistId})"><img src="${newAlbum.imagePath}" alt="Image" class="img-responsive albumPic"></a>        
        <a href="" onclick="viewAlbum({newAlbum.artistId})"><h4>${newAlbum.albumName}</h4></a>
      </div>      
    </c:forEach>
  </div>
      
  <div class="container-fluid">
    <h3>Discover Artists</h3>
    <c:forEach items="${discoverArtists}" begin="0" end="3" var="discoverArtist">
      <div class="col-sm-3 albumCard">
        <a href="" onclick="viewArtist(${discoverArtist.artistId})"><img src="${discoverArtist.imagePath}" alt="Image" class="img-responsive albumPic"></a>
        <a href="" onclick="viewArtist(${discoverArtist.artistId})"><h4>${discoverArtist.artistName}</h4></a>
      </div>
    </c:forEach>
  </div>
          
  <div class="container">
    <h3>Discover Albums</h3>
    <c:forEach items="${discoverAlbums}" begin="0" end="3" var="discoverAlbum">
      <div class="col-sm-3 albumCard">
          <a href="" onclick="viewAlbum(${discoverAlbum.albumId})"><img src="${discoverAlbum.imagePath}" alt="Image" class="img-responsive albumPic"></a>             
          <a href="" onclick="viewAlbum(${discoverAlbum.albumId})"><h4>${discoverAlbum.albumName}</h4></a>
          <a href="" onclick="viewArtist(${discoverAlbum.artistId.artistId})"><h6>${discoverAlbum.artistId.artistName}</h6></a>
      </div>      
    </c:forEach>
  </div>
</div>