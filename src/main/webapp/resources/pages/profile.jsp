<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="row">
  <div class="col-xs-2"  id="user-img-div">
    <a href="#"><img src="${viewedUser.imagePath}" class="img-circle userPic" id="user-img"></a>
  </div>
  <div id="user-type"><p>User</p></div>
  <div id="user-txt"><p>${viewedUser.username}</p></div>

  
  <div class="userFollow">
    <c:choose>
      <c:when test="${viewedUser.username!=currentUser.username}">
          <button class="btn btn-primary" id="followUser" >Follow</button>
        
      </c:when>
    </c:choose>
  </div>
  <div class="row" id="profileSections">
  <ul class="nav nav-pills nav-justified" id="profilePill">
    <!-- onClick changes selected element -->
    <li role="presentation" class="active">
      <a data-toggle="pill" href="#overview" class="cat-option">Overview</a>                      
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#recent" class="cat-option">Recently Played</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#publicPlaylists" class="cat-option" id="profilePlay">Public Playlists</a>                      
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#following" class="cat-option">Following</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#followers" class="cat-option">Followers</a>     
    </li>
    <c:choose>
    <c:when test="${viewedUser.accountType == 'Admin'}">
      <li role="presentation">
        <a data-toggle="pill" href="#admin" class="cat-option" id="adminPill">Admin</a>     
      </li>      
    </c:when>
      <c:when test="${viewedUser.accountType == 'Artist'}">
      <li role="presentation">
        <a data-toggle="pill" href="#artist" class="cat-option" id="artistPill">Artist</a>     
      </li>      
    </c:when>
    <c:otherwise></c:otherwise>
  </c:choose>
  </ul>
</div>
  
  <div id="profilePane" user="${viewedUser.username}">
    

  </div>
  
  </div>
</div>
