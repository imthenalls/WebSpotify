<div id="browseGenres">
  <div class="row">
    <c:forEach items="${genrePlaylists}" var="genrePlaylist">
      <a href="#">
          <div class="col-xs-3">
              <img src="resources/img/genres/rock.png" alt="Rock">
              <h3>${genrePlaylist.playlistName}</h3>
          </div>                            
      </a>
    </c:forEach>
  </div>
</div>
