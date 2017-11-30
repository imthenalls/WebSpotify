<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div class="col-xs-2">
      <img class="mediaPic" src="/resources/img/foo.jpg">
    </div>
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <span class="mediaType">user</span>
      </div>
    </div>
  </div>
</div>

<div>
  <h3>Users</h3>
  <c:forEach items="${userList}" var="user">
    <div class="col-md-3">
       <a href="#">${user.username}</a></td>

    </div>
  </c:forEach>
</div>