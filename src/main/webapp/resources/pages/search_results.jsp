<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div id="search-pane">
  
    <div id="search-results-header" class="row"><h1>Search Results</h1></div>
    
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
              <c:choose>
                <c:when test="${currentUser.isFollowingSong(topResultSong)}">
                  <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${topResultSong.albumId.albumId},'search_results.jsp')"></button>
                </c:when>
                <c:otherwise>
                  <button class="fa fa-plus albumButton" onclick="followAlbum(${topResultSong.albumId.albumId},'search_results.jsp')"></button>                  
                </c:otherwise>
              </c:choose>

              <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
              <ul class='dropdown-menu'>
                <c:choose>
                  <c:when test="${currentUser.isFollowingSong(topResultSong)}">
                    <li><a href="#" onclick="unfollowAlbum(${topResultSong.albumId.albumId},'search_results.jsp')">Unfollow</a></li>
                  </c:when>
                  <c:otherwise>
                    <li><a href="#" onclick="followAlbum(${topResultSong.albumId.albumId},'search_results.jsp')">Follow</a></li>                 
                  </c:otherwise>
                </c:choose>                
                <li><a href='#' onclick="viewArtist(${topResultSong.artistId.artistId})">View Artist</a></li>
              </ul>
              </div>
            </div>
            <a href="#" onclick="viewAlbum(${topResultSong.albumId.albumId})"><h4>${topResultSong.title}</h4></a>
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
                <c:choose>
                  <c:when test="${currentUser.isFollowingArtist(Artist)}">
                    <button class="fa fa-remove albumButton unfollowArtist" onclick="unfollowArtist(${Artist.artistId},'search_results.jsp')"></button>
                  </c:when>
                  <c:otherwise>
                    <button class="fa fa-plus albumButton followArtist" artistId="${Artist.artistId}" currentPage='search_results.jsp'></button>
                  </c:otherwise>
                </c:choose>
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
            <c:choose>
              <c:when test="${currentUser.isFollowingAlbum(Album)}">
                <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${Album.albumId},'search_results.jsp')"></button>
              </c:when>
              <c:otherwise>
                <button class="fa fa-plus albumButton" onclick="followAlbum(${Album.albumId},'search_results.jsp')"></button>
              </c:otherwise>
            </c:choose>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <c:choose>
                <c:when test="${currentUser.isFollowingAlbum(Album)}">
                  <li><a href="#" onclick="unfollowAlbum(${Album.albumId},'search_results.jsp')">Unfollow</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="#" onclick="followAlbum(${Album.albumId},'search_results.jsp')">Follow</a></li>
                </c:otherwise>
              </c:choose>
              <li><a href='#' onclick="viewArtist(${Album.artistId.artistId})">View Artist</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
      </div>
    </c:forEach>
    </div>

    </div>

    <div class="row"></div>
    <div id="song-search-results-header">
      <span class="search-header">Songs</span> <span class="search-text"> <a href="#" id="moreSongs">see more</a></span>
    </div>
    <div id="search-song-results" class="row tableContainer">
      <table class="table songTable">
          <th class="col-md-l">#</th>
          <th class="col-md-4">Title</th>
          <th class="col-md-2">Artist</th>
          <th class="col-md-2">Album</th> 
          <th class="col-md-1 text-right">Options</th>
          <th class="col-md-1 text-right">Duration</th>
          <th class="col-md-1 text-right">Play Count</th>
          <c:forEach varStatus="loop" items="${songList}" var="Song">
            <tr class="tableRow">
              <td>
                  <a class="playHide">
                      ${loop.index+1}
                  </a>        
                <a href="#" onclick="playSong(${Song.songId},'artist',${loop.index})">
                  <i class="playShow fa fa-play fa-fw"></i>
                </a>
              </td>
              <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
              <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
              <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
              <td class="text-right">
                <div class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-ellipsis-h songOptions" id="dropdownMenu1"></i>
                  </a>
                  <ul class="dropdown-menu multi-level">
                    <c:choose>
                      <c:when test="${currentUser.isFollowingSong(Song)}">
                        <li><a href="#" songId="${Song.songId}" currentPage="search_results.jsp" class="unfollowSong">Unfollow Song</a></li>
                      </c:when>
                      <c:otherwise>
                        <li><a href="#" songId="${Song.songId}" currentPage="search_results.jsp" class="followSong">Follow Song</a></li>
                      </c:otherwise>
                    </c:choose>
                    <c:if test="${currentUser.accountType == 'Admin'}">
                     <li><a href="#" onclick=adminRemoveSong(${Song.songId})>Remove From songs</a></li>
                    </c:if>
                    <c:if test="${currentUser.accountType == 'Artist'}">
                      <li><a href="#" onclick=artistRequestSongRemoval(${Song.songId},'search_results.jsp')>Request remove song</a></li>
                    </c:if>
                    <li class="divider">
                    <li class="dropdown-submenu">
                      <a href="#">Add to Playlist</a>
                    </li>
                    <ul class="dropdown-menu">
                      <c:forEach items="${createdPlaylists}" var="Playlist">
                        <li><a href="#" onclick="addSongToPlaylist(${Playlist.playlistID}, ${Song.songId})">${Playlist.playlistName}</a></li>
                      </c:forEach>
                    </ul>
                  </ul>
                </div>
              </td>
              <td class="text-right">
                <fmt:formatNumber value="${(Song.duration/60) - ((Song.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/>
              </td>
              <td class='text-right'>
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
                  <c:choose>
                    <c:when test="${currentUser.isFollowingPlaylist(Play)}">
                      <button class="fa fa-remove albumButton" onclick="unfollowPlaylist(${Play.playlistID},'search_results.jsp')"></button>
                    </c:when>
                    <c:otherwise>
                      <button class="fa fa-plus albumButton" onclick="followPlaylist(${Play.playlistID},'search_results.jsp')"></button>
                    </c:otherwise>
                  </c:choose>
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
            <c:choose>
              <c:when test="${currentUser.isFollowingUser(User)}">
                <button class="fa fa-remove albumButton" onclick="unfollowUser(${User.username},'search_results.jsp')"></button>
              </c:when>
              <c:otherwise>
                <button class="fa fa-plus albumButton" onclick="followUser(${User.username},'search_results.jsp')"></button>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <a href="#"username="${User.username}"><h4>${User.username}</h4></a>
      </div>
    </c:forEach>
    </div>
    </div>
</div>
