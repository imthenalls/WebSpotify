<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
      <div class="row">
        <a href="#">
          <h3 class="mediaName">Unapproved Artists</h3>    
        </a>
      </div>
      <div class="row" id="mediaSpecs">
        <span>
          ${currentPlaylist.description}
        </span>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table playlistTable">
    <tr>
      <th>Artist</th>
       <th>hello</th>
    </tr>
    <c:forEach items="${unapprovedArtists}" var="User">
        <tr class="tableRow">
        <td><a href="#"> ${User.username}</a></td>
         <c:choose>
             <c:when test="${currentUser.accountType == 'Admin'}">
             <td>
             <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
                      Options
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                       <li><a class = "unapproved-artists" href="#" username="${User.username}">Approve artist</a></li>
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
