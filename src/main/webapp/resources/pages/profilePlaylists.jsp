<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
  <div class="container-fluid">
    <c:forEach items="${publicPlaylists}" varStatus='loop' var="Play">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a href="#"><img src="${Play.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
          <div class="albumOverlay">
            <c:choose>
              <c:when test="${currentUser.isFollowingPlaylist(Play)}">
                <button class="fa fa-remove albumButton"></button>
              </c:when>
              <c:otherwise>
                <button class="fa fa-plus albumButton"></button>
              </c:otherwise>
            </c:choose>   
            </div>
          </div>
          <a href="#" onclick="viewPlaylist(${Play.playlistID})"><h4>${Play.playlistName}</h4></a>
      </div>
    </c:forEach>
    </div>
  </div>
