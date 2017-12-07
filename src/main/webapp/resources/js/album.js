$(document).ready(function(){
  $(document).on('click', '.album-card-search', function(){
    viewAlbum($(this).attr("albumId"));
  });

  $(document).on('click', '.song-row-search', function(){
    viewAlbum($(this).attr("albumId"));
  });

});

function viewAlbum(id){
    $.ajax({
        url: "album/viewAlbum",
        type: "GET",
        data: ({
            albumID: id
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/album.jsp",function(){
                
            });
        },
        error: function(){
            console.log("Error viewing album");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewFollowedAlbums(){
  $("#center-pane").load("/resources/pages/followedAlbums.jsp");
}

function followAlbum(albumId,currentPage) {
  $.ajax({
    url: "album/followAlbum",
    type: "POST",
    data: ({
      albumId: albumId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
        console.log("Success following album");
      });
    },
    error: function(){
      console.log("Failure following album");
    }
  });
  return false;
};

function unfollowAlbum(albumId,currentPage) {
  $.ajax({
    url: "album/unfollowAlbum",
    type: "POST",
    data: ({
      albumId: albumId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
                console.log("Success unfollowing album");
            });
    },
    error: function(){
            console.log("Failure unfollowing album");
    }
  });
  return false;
};