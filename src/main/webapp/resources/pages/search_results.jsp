<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="search-pane" class="col-xs-8 col-xs-offset-2" >
    <div id="search-results-header" class="row"><h2>Search Results</h2></div>
    <div id="artist-search-results" class="row">
    <div id="artist-search-results-header"><h3>Artists</h3></div>
    <c:forEach items="${artistList}" var="artist">
    <div class="card artist-card">
        <img class="card-img" src="${artist.imagePath}">
        <div class="card-block">
            <p class="card-text">${artist.artistName}</p>
        </div>
        </div>
    </c:forEach>
    </div>
    <div id="album-search-results" class="row">
        <div id="album-search-results-header"><h3>Albums</h3></div>
    </div>
    <div id="album-search-results-row" class="row">
      <c:forEach items="${albumList}" var="album">
        <div class="col-xs-2 card">
          <img class="card-img" src="${album.imagePath}">
          <div class="card-block">
              <p class="card-text">${album.albumName}</p>
          </div>
        </div>
      </c:forEach>
    </div>
    <div id="search-song-results" class="row">
    <div id="song-search-results-header"><h3>Songs</h3></div>
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
                <tr>
                    <td>${song.title}</td>
                    <td>${song.artistId.artistName}</td>
                    <td>${song.duration}</td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="search-user-results" class="row">
    <div id="user-search-results-header"><h3>Profiles</h3></div>
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