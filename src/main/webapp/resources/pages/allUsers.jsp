<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="/resources/img/foo.jpg">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType"></span>
      </div>
      <div class="row">
        <a href="#">
          <h3 class="mediaName">User Accounts</h3>    
        </a>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table playlistTable">
    <tr>
      <th>Name</th>
      <th>Account Status</th>
      <th>Ban</th>
    </tr>
    <c:forEach items="${userList}" var="User">
        <tr class="tableRow">
        <td><a href="#"> ${User.username}</a></td>
         <c:choose>
             <c:when test="${currentUser.accountType == 'Admin'}">
             <td>
               ${User.accountType}
             </td>
             </c:when>
             <c:otherwise>
                 <td>show nothing</td>
             </c:otherwise>
         </c:choose>
        <td class="tableRow">
          <button class="btn btn-primary ban-button" username="${User.username}">Ban</button>
        </td>
        </tr>
    </c:forEach> 

  </table>
</div>
