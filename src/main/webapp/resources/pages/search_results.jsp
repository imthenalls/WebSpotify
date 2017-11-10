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

        <!-- Bootstrap Core CSS-->
        <link href="css/bootstrap.css" rel="stylesheet">

        <!-- Fonts CSS -->
        <link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

        <!-- Custom Theme CSS -->
        <link rel="stylesheet" href="css/stylesheet.css">


        <!-- jQuery script -->
        <script src="js/jquery.min.js"></script>

        <!-- Bootstrap Javascript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- For including HTML Snippets -->
        <script src="js/w3.js"></script>

        <!-- Main Page Script -->
        <script src="js/script.js"></script>
    </head>
    <body>
        <div>
          <div w3-include-html="toolbars/top.html"></div>
          
            <div id="main-section">
                <div class="col-xs-2" id="left-toolbar">
                    <ul class ="nav list-group">
                        <li>
                            <a href="#">Browse</a>
                        </li>
                        <li>
                             <a href="#">Radio</a>
                        </li>
                    </ul>
                    <ul class ="nav list-group">
                        <li>
                            <a href="#">Your Library</a>
                        </li>
                        <li>
                            <a href="#">Your Daily Mix</a>
                        </li>

                        <li>
                            <a href="#">Recently Played</a>
                        </li>

                        <li>
                             <a href="#">Songs</a>
                        </li>

                        <li>
                            <a href="#">Albums</a>
                        </li>

                        <li>
                            <a href="#">Artists</a>
                        </li>

                        <li>
                            <a href="#">Stations</a>
                        </li>
                   </ul>
                    <ul class ="nav list-group" id="playlists">
                        <li>
                            <a href="#">Create Playlist
                                <i id="makePlaylist" class="fa fa-plus"></i>
                            </a>
                        </li>
                        <!-- Fill with user's playlist -->
                    </ul>
                </div>
                <div id="search-pane" class="col-xs-8 col-xs-offset-2" >
                  <div id="search-results-header" class="row"><h2>Search Results</h2></div>

                  <div id="artist-search-results" class="row">
                    <div id="artist-search-results-header"><h3>Artists</h3></div>
                    <div class="card artist-card">
                      <img class="card-img" src="img/linkin_park_artist.jpg">
                      <div class="card-block">
                        <p class="card-text">Linkin Park</p>
                      </div>
                    </div>
                  </div>

                  <div id="album-search-results" class="row">
                    <div id="album-search-results-header"><h3>Albums</h3></div>
                  </div>
                  <div id="album-search-results-row" class="row">
                    <div class="col-xs-2 card">
                      <img class="card-img" src="img/hybrid_theory_album.jpg">
                      <div class="card-block">
                        <p class="card-text">Hybrid Theory</p>
                      </div>
                    </div>
                    <div class="col-xs-2 card">
                      <img class="card-img" src="img/hunting_party_album.jpg">
                      <div class="card-block">
                        <p class="card-text">Hunting Party</p>
                      </div>
                    </div>
                  </div>
                  <div id="search-song-results" class="row">
                  <div id="song-search-results-header"><h3>Songs</h3></div>
                    <table class="table songTable">
                        <thead>
                          <tr>
                            <th>Song Name</th>
                            <th>Artist</th>
                            <th>Duration</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>Papercut</td>
                            <td>Linkin Park</td>
                            <td>4 seconds</td>
                          </tr>
                          <tr>
                            <td>Faint</td>
                            <td>Linkin Park</td>
                            <td>23 seconds</td>
                          </tr>
                        </tbody>
                      </table>
                  </div>
                </div>



                <div w3-include-html="toolbars/right.html"></div>
            </div>
            <div w3-include-html="toolbars/bottom.html"></div>
        </div>

            <script>

            </script>
    </body>
</html>
