<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
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