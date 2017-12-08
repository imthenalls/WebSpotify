<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
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
      <th>Ban/Unban</th>
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
          <td>
           <div class="dropdown">
             <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
               Options
               <span class="caret"></span>
             </button>
             <ul class="dropdown-menu">
               <c:choose>
                 <c:when test="${User.accountType == 'Banned'}">
                   <li><a class="ban-button" username="${User.username}">Unban</a></li>
                 </c:when>
                 <c:otherwise> 
                   <li><a class="ban-button" username="${User.username}">Ban</a></li>
                 </c:otherwise>
                 </c:choose>
               <li><a class = "delete-user" href="#" username="${User.username}">delete user</a></li>
             </ul>
           </div>
          </td>
        </tr>
    </c:forEach> 

  </table>
</div>
