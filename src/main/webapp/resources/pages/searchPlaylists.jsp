<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Search for Album: ${lastSearch}</h1>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div>
  <div class="container-fluid">
          <c:forEach items="${publicPlaylists}" varStatus='loop' var="Play">
            <div class="col-md-3" style="height:380px;">
              <div class="albumCard">
                <a href="#"><img src="${Play.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic"></a>
                <a href="#"><h4>${Play.playlistName}</h4></a>
            </div>
          </c:forEach>
        </div>
  </div>
