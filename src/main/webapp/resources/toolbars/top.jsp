<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand click" href="#browseToggle" onclick="viewBrowse()" style='color:#3399ff'>
                Team 0n3
            </a>
            <form class="navbar-form navbar-left" action="/resources/pages/search_results.jsp" role="search" id="searchForm">
                <div class="form-group">
                    <input type="text" class="form-control" id="keyword" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default search-btn">Search</button>
            </form>
        </div>
       <div class="pull-right">
            <div class="dropdown"> <span class="fa fa-chevron-down dropdown-toggle" data-toggle="dropdown"></span>

                <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1">
                  <li><a href="#" class="viewHelp">Help</a></li>
                  <li><a href="#">Private Session - not done</a></li>
                  <li><a href="#" class="settings-drop">Settings</a> </li>
                  <li><a href="/logoutUser">Logout</a></li>
                </ul>
            </div>
        </div>
    
        <div class="nav navbar-right">
            <p><a href="#" class="click viewUser" username="${currentUser.username}">${currentUser.username}</a></p>
        </div>
        <div class="nav navbar-right">
            <div id="container">
              <a href="#" class="click viewUser" username="${currentUser.username}"><img src="${currentUser.imagePath}" class="img-responsive img-circle" id="user-img-sm"></a>
            </div>
        </div>
    </div>
</nav>
