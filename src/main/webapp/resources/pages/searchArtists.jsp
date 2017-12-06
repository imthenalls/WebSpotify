<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Search for Artist: ${lastSearch}</h1>   
    </div>
  </div>
</div>
<div>
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
