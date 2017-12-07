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
      <a data-toggle="pill" href="#hi" class="cat-option">Recently Played</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#hello" class="cat-option" id="profilePlay">Public Playlists</a>                      
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#howdy" class="cat-option">Following</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#sup" class="cat-option">Followers</a>     
    </li>                 
    
  </ul>
</div>
  
  <div class="tab-content" id="profilePane">
    
  <div id="profileOverview" class="tab-pane fade active in">

  </div>
  <div id="profileRecent" class="tab-pane fade">
    <h3>Recently Listened To</h3>
      <hr />
  </div>
  <div id="profilePlaylists" class="tab-pane fade" user='${currentUser.username}'>
    <h3>Playlist</h3>
      <hr />
  </div>
  <div id="profileFollowing" class="tab-pane fade">
    <h3>Following</h3>
      <hr />
  </div>
  <div id="profileFollowers" class="tab-pane fade">
    <h3>Followers</h3>
      <hr />
  </div>                    
                         
</div>
  
  </div>
</div>
