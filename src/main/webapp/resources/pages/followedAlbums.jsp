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
    <c:forEach items="${followAlbum}" var="Album">
      <div class="col-md-3" style="height:380px;">
        <div class="albumCard">
          <a class="albumPic" href="#" onclick="viewAlbum(${Album.albumId})"><img src="${Album.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive"></a>
          <a href="#" onclick="viewAlbum(${Album.albumId})"><h4>${Album.albumName}</h4></a>
        </div>
        </div>
    </c:forEach>
  </div>
</div>