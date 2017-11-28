<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Albums</h2>   
      <div class="row" id="mediaSpecs">
        <!-- Insert Play Button -->
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table songTable">
    <tr>
      <th>Title</th>
      <th>Artist</th>
    </tr>
    <c:forEach items="${followAlbum}" var="Album">
      <tr>
        <td><a href="#" onclick="viewAlbum(${Album.albumId})">${Album.albumName}</a></td>
        <td><a href="#" onclick="viewArtist(${Album.artistId.artistId})">${Album.artistId.artistName}</a></td>
      </tr>
    </c:forEach> 
  </table>
</div>