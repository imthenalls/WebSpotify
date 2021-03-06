<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Albums in Your Library</h1>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div>
  <div class="container-fluid">
    <c:forEach items="${followedAlbums}" varStatus='loop' var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${Album.albumId},'followedAlbums.jsp')"></button>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <li><a href="#" onclick="unfollowAlbum(${currentAlbum.albumId},'followedAlbums.jsp')">Unfollow</a></li>
              <li><a href='#' class='viewArtist' artist='${currentAlbum.artistId.artistId}'>View Artist</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
      </div>
    </c:forEach>
  </div>
</div>