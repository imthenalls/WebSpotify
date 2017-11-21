<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand click" href="#browseToggle" style='color:#3399ff'>
                Team 0n3
            </a>
            <form class="navbar-form navbar-left" action="/resources/pages/search_results.jsp" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
        </div>
        <div class="nav navbar-right">
            <div id="user-icon">
                <i id="user-icon-img" class="fa fa-user"></i>
            </div>
        </div>
        <div><span>
                Don't have an account? Sign up <a href="<c:url value="/doLogout" />">Logout</a>
                </span></div>
        <div class="nav navbar-right">
            <p><a class="click" href="#profileToggle">${currentUser.username}</a></p>
        </div>
    </div>
</nav>