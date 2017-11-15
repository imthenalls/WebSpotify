<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-2" id="left-toolbar">
    <ul class ="nav list-group">
        <li class="activeToolbar">
            <a class="click" data-toggle="tab" href="#browseToggle">Browse</a>
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
             <a data-toggle="tab" href="#">Songs</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Albums</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Artists</a>
        </li>
        <li> 
            <a data-toggle="tab" href="#">Stations</a>
        </li>
        <li>
            <span style="color:#3399ff;">Your Playlists</span>
        </li>
        <li>
            <a data-toggle="modal" href="#createPlaylistModal">Create Playlist
                <i id="makePlaylist" class="fa fa-plus"></i>
            </a>
        </li>
        
        <!-- Fill with user's playlist -->
        <c:forEach items="${PlaylistList}" var="Playlist">
            <li>
                <a id="p${Playlist.playlistID}" class="click playlistItem" data-toggle="tab" href="#playlistToggle">${Playlist.playlistName}</a>
            </li>   
        </c:forEach>           
    </ul>
</div>