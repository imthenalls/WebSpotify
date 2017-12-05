<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand click" href="#browseToggle" style='color:#3399ff'>
                Team 0n3
            </a>
            <form class="navbar-form navbar-left" action="/resources/pages/search_results.jsp" role="search" id="searchForm">
                <div class="form-group">
                    <input type="text" class="form-control" id="keyword" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
        </div>
        <div class="nav navbar-right">
            <div>
                <a href="/logoutUser"><i class="fa fa-power-off"></i></a>
            </div>
        </div>
        <div class="nav navbar-right">
            <p><a href="#" class="click" onclick="viewProfile()">${currentUser.username}</a></p>
        </div>
        <div class="nav navbar-right">
            <div id="container">
                <img src="${currentUser.imagePath}" class="img-responsive img-circle" id="user-img-sm">
            </div>
        </div>
    </div>
</nav>