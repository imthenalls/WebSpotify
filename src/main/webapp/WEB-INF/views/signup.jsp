<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign Up</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/signin.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/stylesheet.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>
</head>
  <body  >
    <div class="container" style="text-align:center">
      <form class="form-signin"action="doSignup" method="post">
        <h2 >Signup</h2>
        <div class="" >
          <input type="text" id="username" name="username" placeholder="Username" class="form-control" required autofocus>
          <input type="text" id="email" name="email" placeholder="Email" class="form-control" required>
          <input type="password" id="password" name="password" placeholder="Password" class="form-control"  required>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        </div>
      </form>
    </div> <!-- /container -->
  </body>
</html>
