<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Browse Overview</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- STYLESHEETS -->
        
        <!-- Bootstrap Core CSS-->
        <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
        
        <!-- Fonts CSS -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        
        <!-- Custom Theme CSS -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/stylesheet.css">
        
    </head>
    <body>
        <div>
            <%@include file="/resources/toolbars/top.jsp" %>
            
            <div id="main-section">
                <%@include file="/resources/toolbars/left.jsp" %>
                
                <div class="col-xs-10 col-xs-offset-2" id="center-pane">
                    <div class="paneChoice" style="display:block;" id="browseToggle">
                        <%@ include file="/resources/pages/browsePage.jsp" %>
                    </div>
                    <div class="paneChoice" style="display:none;" id="radioToggle"">
                        <%@ include file="/resources/pages/radio.jsp" %>
                    </div>
                    
                    <div class="paneChoice" style="display:none;" id="playlistToggle">
                        <%@ include file="/resources/pages/playlist.jsp" %>
                    </div>
                    
                    <div class="paneChoice" style="display:none;" id="profileToggle">
                        <%@ include file="/resources/pages/profile.jsp" %>
                    </div>
                    
                    <!-- INSERT EVERY POSSIBLE PAGE FOR CENTER PANE -->
                 
                </div>
            </div>
            
            <%@include file="/resources/toolbars/bottom.jsp" %>
        </div>
        
        <!-- JAVASCRIPT -->
        
        <!-- jQuery script -->
        <script src="resources/js/jquery.min.js"></script>
        
        <!-- Bootstrap Javascript -->
        <script src="resources/js/bootstrap.min.js"></script>
        
        <!-- For including HTML Snippets -->
        <script src="resources/js/w3.js"></script>
        
        <!-- Main Page Script -->
        <script src="resources/js/script.js"></script>

    </body>
</html>
