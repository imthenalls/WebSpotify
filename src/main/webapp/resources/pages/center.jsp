<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- OPTION FOR COLLAPSIBLE FEATURED SECTION -->
<!-- <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#featured-media">Hide/Show</button> -->

<div class="row" id="browse-categories">
  <ul class="nav nav-pills nav-justified" id="navPill">
    <!-- onClick changes selected element -->
    <li role="presentation" class="active">
      <a data-toggle="pill" href="#overview" class="cat-option">Overview</a>                      
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#charts" class="cat-option">Charts</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#genres" class="cat-option">Genres</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#new" class="cat-option">New on Team0n3</a>     
    </li>
    <li role="presentation">
      <a data-toggle="pill" href="#discover" class="cat-option">Discover</a>     
    </li>                   
  </ul>
</div>

<div class="tab-content">
  <div id="overview" class="tab-pane fade active in">
    <%@include file="/resources/pages/browseTabs/overview.jsp" %>
  </div>
  <div id="charts" class="tab-pane fade">
    <%@include file="/resources/pages/browseTabs/charts.jsp" %>
  </div>
  <div id="genres" class="tab-pane fade">
    <%@include file="/resources/pages/browseTabs/genres.jsp" %>
  </div>
  <div id="new" class="tab-pane fade">
    <%@include file="/resources/pages/browseTabs/new.jsp" %>
  </div>                    
  <div id="discover" class="tab-pane fade">
    <%@include file="/resources/pages/browseTabs/discover.jsp" %>
  </div>                          
</div>