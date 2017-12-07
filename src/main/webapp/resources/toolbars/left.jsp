<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-2" id="left-toolbar">
    <ul class ="nav list-group">
        <li class="activeToolbar">
            <a href="#" onClick="viewBrowse()">Browse</a>
        </li>
        <li> 
            <span style="color:#3399ff;">Your Library</span>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Your Daily Mix</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Recently Played</a>
        </li>
        <li>
          <a data-toggle="tab" href="#" onclick="viewFollowedSongs()">Songs</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick="viewFollowedAlbums()">Albums</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick='viewFollowedArtists()'>Artists</a>
        </li>
        <li> 
          <a data-toggle="tab" href="#" onclick="adminViewAllSongs()">Admin Songs</a>
        </li>
        <li> 
          <a data-toggle="tab" href="#" onclick="adminViewAllArtists()">All Artists</a>
        </li>
         <li> 
            <a data-toggle="tab" href="#" onclick="viewAdminAllAlbums()">Admin Albums</a>
        </li>
         <li> 
            <a data-toggle="tab" href="#" onclick="adminViewUnapprovedUsers()">Admin Unapproved Users</a>
        </li>
         <li> 
            <a data-toggle="tab" href="#" onclick="adminViewAllUsers()">Admin View All</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" id="view-unapproved-artists" onclick="adminViewUnapprovedArtists()">Admin Unapproved Artists</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick="adminViewAllPlaylists()">ALL Playlists</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick="viewUnpaidSongs()">Unpaid Songs</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick="viewPendingRoyaltyPayments()">Pending Payments</a>
        </li>
        <li>
            <span style="color:#3399ff;">Your Playlists</span>
        </li>
        <li>
          <a data-toggle="modal" href="#createPlaylistModal">Create Playlist
            <i id="playlistButton" class="fa fa-plus"></i>
          </a>
        </li>
        
        <!-- Fill with user's playlist -->
        <c:forEach items="${createdPlaylists}" var="Playlist">
            <li>
                <a href="#" onclick="viewPlaylist(${Playlist.playlistID})" data-toggle="tab">${Playlist.playlistName}</a>
            </li>   
        </c:forEach>  
            
        <c:forEach items="${followedPlaylists}" var="Playlist">
            <li>
                <a href="#" onclick="viewPlaylist(${Playlist.playlistID})" data-toggle="tab">${Playlist.playlistName}</a>
            </li>   
        </c:forEach> 
    </ul>
</div>
