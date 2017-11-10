<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign Up</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>
</head>
    <body>
      <div class="container" style="text-align:center">
        <form class="form-artist" action="doAddArtist" method="post">
          <h2 >Artist Add</h2>
          <div class="" >
              <input type="text" id="artistName" name="artistName" placeholder="ArtistName" class="form-control" required autofocus>
              <button class="btn btn-lg btn-primary btn-block" type="submit">Add Artist</button>
              
          </div>
         
        </form>
          <div class="" action="doAddArtist" method="get">
            <c:forEach items="${ArtistList}" var="artist">
                ${artist}<br>
            </c:forEach>
          </div>
    </div> <!-- /container -->
    </body>
</html>
