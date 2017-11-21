<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign Up</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/kevin-script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>
</head>
    <body>
      <div class="container" style="text-align:center">
           <form id = "newSongToPlaylistForm" class="form-artist" >
                <h2 >Add to Playlist </h2>
                <div class="" >
                    <input type="text" id="songId" name="songId" placeholder="songId" class="form-control" required autofocus>
                    <input type="text" id="playlistId" name="playlistId" placeholder="playlistId" class="form-control" required autofocus>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Add song to playlist</button>
                </div>
          </form>
          <div class="">
            <c:forEach items="${SongList}" var="Song">
                Song = ${Song.title}<br>
            </c:forEach>
            
            <c:forEach items="${albumSongs}" var="Song">
                Album = ${Song.title}<br>
            </c:forEach>
                
            <c:forEach items="${playlistSongs}" var="playlist">
                playlist = ${playlist.playlistName}<br>
            </c:forEach>
            <%--    
            
                
            <c:forEach items="${SongList}" var="Song">
                Song = ${Song.title}<br>
            </c:forEach>  
                
            <c:forEach items="${ConcertList}" var="Concert">
                Concert = ${Concert.concertName}<br>
            </c:forEach>  
            <c:forEach items="${VenueList}" var="Venue">
                Venue = ${Venue.venueName}<br>
            </c:forEach>    
            --%>
          </div>
          
    </div> <!-- /container -->
    </body>
</html>
