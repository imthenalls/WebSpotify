<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Top Albums</h1>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div>
  <div class="container-fluid">
    <c:forEach items="${topAlbums}" varStatus='loop' var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">     
            <c:choose>
              <c:when test="${currentUser.isFollowingAlbum(Album)}">
                <button class="fa fa-remove albumButton" onclick="unfollowAlbum(${Album.albumId},'topAlbums.jsp')"></button>
              </c:when>
              <c:otherwise>
                <button class="fa fa-add albumButton" onclick="followAlbum(${Album.albumId},'topAlbums.jsp')"></button>
              </c:otherwise>
            </c:choose>
            
            <button class="fa fa-play albumButton"></button>
            <button class="fa fa-ellipsis-h albumButton dropdown-toggle" data-toggle='dropdown'></button>
            <ul class='dropdown-menu'>
              <li><a href="#" onclick="unfollowAlbum(${currentAlbum.albumId},'topAlbums.jsp')">Unfollow</a></li>
              <li><a href='#' onclick="viewArtist(${currentAlbum.artistId.artistId})">View Artist</a></li>
            </ul>
            </div>
          </div>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
          <a href="#" onclick="viewArtist(${Album.artistId.artistId})"><h6>${Album.artistId.artistName}</h6></a>
      </div>
    </c:forEach>
    </div>
  </div>