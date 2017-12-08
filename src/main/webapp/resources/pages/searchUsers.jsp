<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
          <h1 class="mediaName">Search for Artist: ${lastSearch}</h1>   
    </div>
  </div>
</div>
<div>
  <div class="container-fluid">
    <c:forEach items="${userList}" varStatus='loop' var="User">
      <div class="col-md-3 viewUser" username="${User.username}" style="height:380px;">
        <div class="albumCard">
          <a href="#"><img src="${User.imagePath}" onerror="this.src='http://placehold.it/350x350'" alt="Image" class="img-responsive albumPic img-circle"></a>
          <div class="albumOverlay">
            </div>
        </div>
        <a href="#"><h4>${User.username}</h4></a>
      </div>
    </c:forEach>
    </div>
  </div>
