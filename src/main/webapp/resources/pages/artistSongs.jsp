<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Search for Song: ${lastSearch}</h2>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div class="row" id="table">
  <table class="table songTable">
    <tr>
      <th>Title</th>
      <th>Artist</th>
      <th>Album</th> 
      <th>Duration</th>
      <th></th>
    </tr>
    <c:forEach items="${artistSongs}" var="Song" begin="0" end="34">
      <tr>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.title}</a></td>
        <td><a href="#" onclick="viewArtist(${Song.artistId.artistId})">${Song.artistId.artistName}</a></td>
        <td><a href="#" onclick="viewAlbum(${Song.albumId.albumId})">${Song.albumId.albumName}</a></td>
        <td>            
          <fmt:formatNumber value="${Song.duration/60}" maxFractionDigits="0"/>
          :
          <fmt:formatNumber value="${Song.duration%60}" minIntegerDigits="2"/></td>
          <c:choose>
             <c:when test="${currentUser.accountType == 'Artist'}">
             <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
                      Options
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick=artistRequestSongRemoval(${Song.songId},'artistSongs.jsp')>Request remove song</a></li>
                    </ul>
                </div>
             </td>
             </c:when>
             <c:otherwise>
                 <td>show nothing</td>
             </c:otherwise>
         </c:choose>
      </tr>
    </c:forEach> 
  </table>
</div>