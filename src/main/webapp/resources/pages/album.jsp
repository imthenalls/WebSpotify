<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src=${currentAlbum.imagePath}>
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">Album</span>
      </div>
      <div class="row">
        <a href="#">
          <h3 class="mediaName">${currentAlbum.albumName}</h3>    
        </a>
      </div>
      <div class="row">
        <a href="#" onclick="viewArtist(${currentAlbum.artistId.artistId})">By ${currentAlbum.artistId.artistName}</a>
      </div>
      <div class="row">
        <div class="dropdown">
          <button class="btn btn-primary" id="playlistPlayButton">Play</button>
          <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Options
            <span class="fa fa-chevron-circle-down"></span>
          </button>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table songTable">
    <tr>
      <th>#</th>
      <th>Title</th>
      <th>Duration</th>
    </tr>
    <c:forEach items="${albumSongs}" varStatus="loop" var="Song">
      <tr class="tableRow">
        <td class="">
            <a class="playHide">
                ${loop.index+1}
            </a>        
          <a href="#">
            <i class="playShow fa fa-play fa-fw"></i>
          </a>
        </td>
        <td>${Song.title}</td>
        <td class="durationColumn">${Song.duration}</td>
      </tr>
    </c:forEach> 
  </table>
</div>
