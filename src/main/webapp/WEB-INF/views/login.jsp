<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="icon" href="'/resources/img/team0n3.png'">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/stylesheet.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>
</head>
  <body>
    <div class="container" style="text-align:center">
      <form class="form-signin" action="loginUser" method="post">
        <c:if test="${badLogin}">
          <span>Username or password is incorrect</span>
        </c:if>
        <h2 >Please log in</h2>
        <div class="" align="center">
          <input type="text" id="username" name="username" placeholder="Username" class="form-control" required autofocus>
          <input type="password" id="password" name="password" placeholder="Password" class="form-control"  required>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
          <div align="center">
            <span>
            Don't have an account? Sign up <a href="${pageContext.servletContext.contextPath}/viewSignup" >here</a>
            </span>
          </div>
        </div>
      </form>

    </div> <!-- /container -->
  </body>
</html>
