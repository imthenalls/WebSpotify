<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-2" id="left-toolbar">
    <ul class ="nav list-group">
        <li class="activeToolbar">
            <a href="#" onClick="viewBrowse()">Browse</a>
        </li>
        <li>
             <a class="click" data-toggle="tab" href="#radioToggle">Radio</a>
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
            <a data-toggle="tab" href="#">Artists</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Stations</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#" onclick="viewAllPlaylists()">Followed Playlists</a>
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
        <c:forEach items="${PlaylistList}" var="Playlist">
            <li>
                <a href="#" onclick="viewPlaylist(${Playlist.playlistID})" data-toggle="tab">${Playlist.playlistName}</a>
            </li>   
        </c:forEach>  
            
        <c:forEach items="${FollowedPlaylists}" var="Playlist">
            <li>
                <a href="#" id="p${Playlist.playlistID}" onclick="viewPlaylist(this.id)" data-toggle="tab">${Playlist.playlistName}</a>
            </li>   
        </c:forEach> 
    </ul>
</div>
