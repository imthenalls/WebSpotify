<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search-pane">
  
    <div id="search-results-header" class="row"><h2>Search Results</h2></div>
    
    <c:choose>
    <c:when test="${not empty topResultArtist}">
      <div id="search-results-header" class="row"><h2>Top Result Artist:</h2></div>
      <div class="container-fluid">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewArtist(${topResultArtist.artistId})"><img src="${topResultArtist.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowArtist(${topResultArtist.artistId},'followedArtists.jsp')"></button>
            <button class="fa fa-play albumButton"></button>
            </div>
        </div>
          <a href="#" onclick="viewArtist(${topResultArtist.artistId})"><h4>${topResultArtist.artistName}</h4></a>
        </div>
      </div>
    </c:when>
    <c:when test="${not empty topResultSong}">
      <div id="search-results-header" class="row"><h2>Top Result Song:</h2></div>
      <div class="container-fluid">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${topResultSong.albumId.albumId})"><img src="${topResultSong.albumId.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${topResultSong.albumId.albumId},'followedAlbums.jsp')"></button>
            <button class="fa fa-play albumButton"></button>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <li><a href="#" onclick="unfollowAlbum(${topResultSong.albumId.albumId},'album.jsp')">Unfollow</a></li>
              <li><a href='#' id="addAlbumToQueue" album="${topResultSong.albumId.albumId}">Add to Queue - not done</a></li>
              <li><a href='#' class='viewArtist' artist='${topResultSong.albumId.artistId.artistId}'>View Artist - not done</a></li>
              <li><a href='#'>Add to Playlist - not done</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewAlbum(${topResultSong.albumId.albumId})"><h4>${topResultSong.albumId.albumName}</h4></a>
      </div>
    </div>
    </c:when>
    <c:otherwise>
        
    </c:otherwise>
</c:choose>
    <div id="artist-search-results" class="row">
    <div id="artist-search-results-header">
      <span class="search-header">Artists</span> <span class="search-text"> <a href="#" id="moreArtists">see more</a></span>
    </div>
      <div class="container-fluid">
    <c:forEach items="${artistList}" varStatus='loop' var="Artist">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewArtist(${Artist.artistId})"><img src="${Artist.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowArtist(${Artist.artistId},'followedArtists.jsp')"></button>
            <button class="fa fa-play albumButton"></button>
            </div>
        </div>
        <a href="#" onclick="viewArtist(${Artist.artistId})"><h4>${Artist.artistName}</h4></a>
      </div>
    </c:forEach>
    </div>

    </div>
    
    <div id="album-search-results" class="row">
    <div id="album-search-results-header"><span class="search-header">Albums</span> <span class="search-text"> <a href="#" id="moreAlbums">see more</a></span></div>
      <div class="container-fluid">
    <c:forEach items="${albumList}" varStatus='loop' var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${Album.albumId},'followedAlbums.jsp')"></button>
            <button class="fa fa-play albumButton"></button>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <li><a href="#" onclick="unfollowAlbum(${currentAlbum.albumId},'album.jsp')">Unfollow</a></li>
              <li><a href='#' id="addAlbumToQueue" album="${currentAlbum.albumId}">Add to Queue - not done</a></li>
              <li><a href='#' class='viewArtist' artist='${currentAlbum.artistId.artistId}'>View Artist - not done</a></li>
              <li><a href='#'>Add to Playlist - not done</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
      </div>
    </c:forEach>
    </div>

    </div>
    
    <div class="row"></div>
    <div id="search-song-results" class="row">
    <div id="song-search-results-header">
      <span class="search-header">Songs</span> <span class="search-text"> <a href="#" id="moreSongs">see more</a></span>
    </div>
        <table class="table songTable">
            <thead>
                <tr>
                    <th>Song Name</th>
                    <th>Artist</th>
                    <th>Duration</th>
                </tr>
            </thead>
            <tbody>
              <c:forEach items="${songList}" var="song">
                <tr class="song-row-search" albumId="${song.albumId.albumId}">
                    <td>${song.title}</td>
                    <td><a href="#" onclick="viewArtist(${song.artistId})">${song.artistId.artistName}</a></td>
                    <td>${song.duration}</td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="search-playlist-results" class="row">
      <div id="playlist-search-results-header"><span class="search-header">Playlists</span> <span class="search-text"> <a href="#" id="morePlaylists">see more</a></span></div>
        <div class="container-fluid">
          <c:forEach items="${publicPlaylists}" varStatus='loop' var="Play">
            <div class="col-md-3" style="height:380px;">
              <div class="albumCard">
                <a href="#"><img src="${Play.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
                <div class="albumOverlay">     
                  <button class="fa fa-remove albumButton"></button>
                  <button class="fa fa-play albumButton"></button>
                  <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
                  <ul class='dropdown-menu'>
                    <li><a href='#'>Add to Playlist - not done</a></li>
                  </ul>
                  </div>
                </div>
                <a href="#"><h4>${Play.playlistName}</h4></a>
            </div>
          </c:forEach>
        </div>
      
    </div>
    
    

    
    
    
    <div id="search-user-results" class="row">
    <div id="user-search-results-header"><span class="search-header">Users</span> <span class="search-text"> <a href="#" id="moreUsers">see more</a></span></div>
          <div class="container-fluid">
    <c:forEach items="${userList}" varStatus='loop' var="User">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" ><img src="${User.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic img-circle"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton"></button>
            <button class="fa fa-play albumButton"></button>
            </div>
        </div>
        <a href="#" class="viewUser" username="${User.username}"><h4>${User.username}</h4></a>
      </div>
    </c:forEach>
    </div>
    </div>
</div>
