<div id="browseGenres">
  <div class="row">
    <h3>Genres</h3>
    <c:forEach items="${allGenres}" var="genre">
      <div class="col-xs-3 albumCard">
        <div class="thumbnail">
          <a href="#" class="viewGenrePlaylist" genre="${genre}"><img src="resources/img/team0n3.png" class="img-responsive albumPic" alt="Rock"></a>
          <a href="#" class="viewGenrePlaylist" genre="${genre}"><h3>${genre}</h3></a>
        </div>
      </div>                            
    </c:forEach>
  </div>
</div>
