<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search-pane">
  
    <div id="search-results-header" class="row"><h2>Search Results</h2></div>
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
    <div id="search-user-results" class="row">
    <div id="user-search-results-header"><span class="search-header"></span> <span class="search-text"> <a href="#" id="morePlaylists">see more</a></span></div>
        <table class="table userTable">
            <thead>
                <tr>
                    <th>Username</th>
                </tr>
            </thead>
            <tbody>
              <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                </tr>
              </c:forEach>

            </tbody>
        </table>
    </div>
</div>
