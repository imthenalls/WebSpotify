<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

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
      <form class="form-signin" action="doLogin" method="post">
        <h2 >Please log in</h2>
        <div class="" align="center">
            <input type="text" id="username" name="username" placeholder="" class="form-control" required autofocus>
            <input type="password" id="password" name="password" placeholder="" class="form-control"  required>
            <div class="checkbox">
                <!-- Rounded switch -->
                <label>
                    <label class="switch"> 
                      <input type="checkbox"> 
                      <span class="slider round"></span> 
                    </label> 
                    Remember me 
                </label> 
       		</div>
            <button id="login" class="btn btn-lg btn-primary btn-block" type="submit"></button>
            <div align="center">
                <span>
                Don't have an account? Sign up <a href="${pageContext.servletContext.contextPath}/signup" >here</a>
                </span>
            </div>
        </div>
      </form>

    </div> <!-- /container -->
    <!-- load labels -->
    <script>
        $(document).ready(function(){
            $.getJSON("resources/json/labels.json",
            function(json){
                $("#username").attr("placeholder",json['username-label']);
                $("#password").attr("placeholder",json['password-label']);
                $("#login").html(json['login-label']);
            });
        });
    </script>
  </body>
</html>
