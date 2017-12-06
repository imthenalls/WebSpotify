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
      <th>Pay Status</th>
      <th>Amount Owed</th>
      <th></th>
    </tr>
    <c:forEach items="${paymentRequests}" var="Payment">
      <tr>
        <td><a href="#" onclick="viewAlbum(${Payment.songId.albumId})">${Payment.songId.title}</a></td>
        <td><a href="#" onclick="viewArtist(${Payment.artistId.artistId})">${Payment.artistId.artistName}</a></td>
       
        <td>${Payment.isPaid}</td>
        <td>${Payment.paymentAmount}</td>
          <c:choose>
             <c:when test="${currentUser.accountType == 'Admin' }">
             <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
                      Options
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick="adminPaySongRoyalties(${Payment.songId.songId},${Payment.artistId.artistId})">admin Pay Artist</a></li>
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