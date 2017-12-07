<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="row">
  <div class="col-xs-2"  id="user-img-div">
    <a href="#"><img src="${currentUser.imagePath}" class="img-circle userPic" id="user-img"></a>
  </div>
  <div id="user-type"><p>User</p></div>
  <div id="user-txt"><p>${currentUser.username}</p></div>

  
  <div class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
      <i class="fa fa-ellipsis-h" id="dropdownMenu3"></i>
    </a>
    <ul class="dropdown-menu">
      <li><a href="#" class="settings-drop">EditProfile</a></li>
    </ul>
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
    <c:when test="${currentUser.accountType == 'Admin'}">
      <li role="presentation">
        <a data-toggle="pill" href="#admin" class="cat-option" id="adminPill">Admin</a>     
      </li>      
    </c:when>
    <c:otherwise></c:otherwise>
  </c:choose>
  </ul>
</div>
  
  <div id="profilePane" user="${currentUser.username}">
    

  </div>
  
  </div>
</div>
