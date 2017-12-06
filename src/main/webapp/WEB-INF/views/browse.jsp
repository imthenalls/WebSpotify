<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %> 
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
    
    <link rel="icon" href="/resources/img/kevinCost.png">
    
    <!-- Bootstrap Core CSS-->
    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <!-- Fonts CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- Custom Theme CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/stylesheet.css">
    
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/card.css">
    
    <link rel="icon" href="'/resources/img/team0n3.png'">
  </head>
  <body>
    <div>
      <div id="topTool">
        <%@include file="/resources/toolbars/top.jsp" %>
      </div>
      <div id="createPlaylistModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <!-- Modal content-->
          <div class="modal-content" id="modalBackground">
            <div class="modal-header">
              <span id="closeSpan"><button id="closeButton" type="button" class="btn fa fa-close" data-dismiss="modal"></button></span>
              <h4 class="modal-title">Create Playlist</h4>
            </div>
            <form id="newPlaylistForm" enctype="multipart/form-data">
              <div class="modal-body">
                <div class="row">
                  <input class="form-control" id='pName' type="text" name="pName" maxlength="50" placeholder="Playlist Title">
                </div>
                <div class="row">
                  <div class="col-xs-6 form-group">
                    <img height="250" width="250" id="playlist-image" src="http://placehold.it/250x250" alt="Image" class="row img-responsive">
                    <input id="file" name="file" size='20' class="row form-control" type="file" accept="image/*">
                  </div>
                  <div class="col-xs-6 form-group">
                    <textarea id="pDesc" class='form-control' type="textArea" rows="4" columns="5" form="newPlaylistForm" maxlength="40" placeholder="Description" name=description"></textarea>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-info" type="submit" value="Submit">Submit</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      
      <div id="editModalLocation"></div>
      
      
      <div id="lyricModal"class="modal fade" role="dialog">
          <div class="modal-dialog">
              <div id="lyricsHere"></div>
          </div>
      </div>
      <div id="main-section">
        <div id="leftTool">
          <%@include file="/resources/toolbars/left.jsp" %>
        </div>
        <div class="col-xs-10 col-xs-offset-2" id="center-pane">
              <!-- JQUERY LOADS COMPONENTS HERE -->
        </div>
      </div>
      <div id="bottomTool">
        <%@include file="/resources/toolbars/bottom.jsp" %>
      </div>
    </div>

    <!-- JAVASCRIPT -->

    <!-- jQuery script -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

    <!-- Bootstrap Javascript -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <!-- For including HTML Snippets -->
    <script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>

    <!-- Main Page Script -->
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/lyrics.js"></script>
    
    
    
    <script src="http://malsup.github.com/jquery.form.js"></script>
    
    <!-- Script to display images-->
    <script>
      $(function () {
        $(":file").change(function () {
          if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = imageIsLoaded;
            reader.readAsDataURL(this.files[0]);
          }
        });
      });
      function imageIsLoaded(e) {
        $('#playlist-image').attr('src', e.target.result);
      };
    /** got from http://jsfiddle.net/vacidesign/ja0tyj0f/**/
    </script>
  </body>
</html>
