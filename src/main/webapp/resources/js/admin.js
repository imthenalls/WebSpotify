$(document).ready(function(){
  $(document).on('click','.unapproved-users', function(){
      adminApproveUser($(this).attr("username"));
  });
  $(document).on('click','.unapproved-artists', function(){
      adminApproveArtist($(this).attr("username"));
  });
  $(document).on('click','#view-unapproved-artists', function(){
      console.log("FUCK U ");
      adminViewUnapprovedArtists();
  });
  
    
}); 

function adminApproveUser(username){
    console.log("asdasdasdasdasdasd");
    $.ajax({
        url: "adminApproveUser",
        type: "POST",
        data: ({
          username: username,
        }),
        success:function(){
          console.log("Success approving user");
              $("#center-pane").load("/resources/pages/unapprovedUsers.jsp");
        },
        error: function(){
                console.log("Failure approving user");
        }
    });
    return false;
}

function adminApproveArtist(username){
    console.log("asdasdasdasdasdasd");
    $.ajax({
        url: "adminApproveArtist",
        type: "POST",
        data: ({
          username: username,
        }),
        success:function(){
          console.log("Success approving artist");
              $("#center-pane").load("/resources/pages/unapprovedArtists.jsp");
        },
        error: function(){
                console.log("Failure approving artist");
        }
    });
    return false;
}

function addArtistAdmin(){
     var artistName = $("#artistName").val();
           var popularity = $("#popularity").val();
           var imagePath = $("imagePath").val();
           $.ajax({
               url: "artist/addArtistAdmin",
               type: "POST",
               data:({
                  artistName: artistName,
                  popularity: popularity,
                  imagePath: imagePath,
               }),
               
               success: function(){},
               error: function(){
                   console.log("Failure");
               }
           });
           return false;    
}

function adminRemoveArtist(artistId){
    $.ajax({
        url: "artist/adminRemoveArtist",
        type: "POST",
        data: ({
          artistId: artistId,
        }),
        success:function(){
          console.log("Success deleting artist");
              $("#center-pane").load("/resources/pages/allArtists.jsp");
        },
        error: function(){
                console.log("Failure deleting artist");
        }
    });
    return false;
}

function adminRemovePlaylist(playlistId){
    $.ajax({
        url: "playlist/adminRemovePlaylist",
        type: "POST",
        data: ({
          playlistId: playlistId,
        }),
        success:function(){
          console.log("Success deleting playlist");
              $("#center-pane").load("/resources/pages/allPlaylists.jsp");
        },
        error: function(){
                console.log("Failure deleting playlist");
        }
    });
    return false;
}

function adminRemoveSong(songId){
    $.ajax({
        url: "song/adminRemoveSong",
        type: "POST",
        data: ({
          songId: songId,
        }),
        success:function(){
          console.log("Success deleting song");
              $("#center-pane").load("/resources/pages/allSongs.jsp");
        },
        error: function(){
                console.log("Failure deleting song");
        }
    });
    return false;
}
function adminRemoveAlbum(albumId){
    $.ajax({
        url: "album/adminRemoveAlbum",
        type: "POST",
        data: ({
          albumId: albumId,
        }),
        success:function(){
          console.log("Success deleting Album");
              $("#center-pane").load("/resources/pages/allAlbums.jsp"); //change
        },
        error: function(){
                console.log("Failure deleting Album");
        }
    });
    return false;
}

function viewAdminAllAlbums(){
    $.ajax({
        url: "album/viewAdminAllAlbums",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/allAlbums.jsp",function(){
            });
        },
        error: function(){
            console.log("Error viewing admin all albums");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function adminViewUnapprovedUsers(){
    $.ajax({
        url: "adminViewUnapprovedUsers",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/unapprovedUsers.jsp",function(){
            });
        },
        error:function(){
          console.log("Error viewing admin  unapproved users");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function adminViewUnapprovedArtists(){
    $.ajax({
        url: "adminViewUnapprovedArtists",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/unapprovedArtists.jsp",function(){
            });
        },
        error: function(){
            console.log("Error viewing admin unapproved artists");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function adminViewAllPlaylists(){
    $.ajax({
        url: "playlist/viewAllPlaylists",
        type: "GET",
        success:function(){
            $("#center-pane").load("resources/pages/allPlaylists.jsp",function(){});
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function adminViewAllSongs(){
    $.ajax({
        url: "song/adminViewAllSongs",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/allSongs.jsp",function(){});
        },
        error: function(){
            console.log("Error viewing all songs");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function adminViewAllArtists(){
    $.ajax({
        url: "artist/viewAllArtists",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/allArtists.jsp",function(){});
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}
