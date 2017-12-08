<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <c:choose>
              <c:when test="${currentUser.isFollowingArtist(topResultArtist)}">
                <button class="fa fa-remove albumButton unfollowArtist" onclick="unfollowArtist(${topResultArtist.artistId},'search_results.jsp')"></button>
              </c:when>
              <c:otherwise>
                <button class="fa fa-plus albumButton followArtist" artistId="${topResultArtist.artistId}" currentPage="${'search_results.jsp'}"></button>
              </c:otherwise>
            </c:choose>
            
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
      <c:forEach begin="0" end="9" varStatus="loop" items="${songList}" var="Song">
        <tr class="tableRow">
          <td class="col-md-2">
              <a class="playHide">
                  ${loop.index+1}
              </a>        
            <a href="#" onclick="playSong(${Song.songId},'artist',${loop.index})">
              <i class="playShow fa fa-play fa-fw"></i>
            </a>
          </td>
          <td class='col-md-4'><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
          <td class="col-md-1 text-right">
            <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
          </td>
          <td class="text-right col-md-1">
            <div class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="fa fa-ellipsis-h songOptions" id="dropdownMenu1"></i>
              </a>
              <ul class="dropdown-menu">
                <c:choose>
                  <c:when test="${currentUser.isFollowingSong(Song)}">
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="unfollowSong">Unfollow Song</a></li>
                  </c:when>
                  <c:otherwise>
                    <li><a href="#" songId="${Song.songId}" currentPage="artist.jsp" class="followSong">Follow Song</a></li>
                  </c:otherwise>
                </c:choose>
                <c:forEach items="${createdPlaylists}" var="Playlist">
                  <li><a href="#" onclick="addSongToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
                </c:forEach>
              </ul>
            </div>
          </td>
          <td class='col-md-1 text-right'>
            ${Song.totalPlays}
          </td>
        </c:forEach>
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
      <div class="col-md-3 viewUser" username="${User.username}" style="height:380px;">
        <div class="albumCard">
          <a href="#" ><img src="${User.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic img-circle"></a>
          <div class="albumOverlay">     

            </div>
        </div>
        <a href="#"username="${User.username}"><h4>${User.username}</h4></a>
      </div>
    </c:forEach>
    </div>
    </div>
</div>
