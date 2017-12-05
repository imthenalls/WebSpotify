<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h2 class="mediaName">Songs</h2>   
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
      <th></th>
    </tr>
    <c:forEach items="${allAlbums}" var="Album">
      <tr>
        <td><a href="#" onclick="viewAlbum(${Album.albumId})">${Album.albumName}</a></td>
        <td><a href="#" onclick="viewArtist(${Album.artistId.artistId})">${Album.artistId.artistName}</a></td>
          <c:choose>
             <c:when test="${currentUser.accountType == 'Admin'}">
             <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
                      Options
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick=adminRemoveAlbum(${Album.albumId})>Remove From Albums</a></li>
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