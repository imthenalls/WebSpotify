$(document).ready(function(){
  $(document).on('click', '.artist-card-search', function(){
        viewArtist($(this).attr("artistId"));
    });
    
  $(document).on({
    click: function(){
      followArtist($(this).attr("artistId"),$(this).attr("currentPage"));
    }
  },'.followArtist');

  $(document).on({
    click: function(){
      unfollowArtist($(this).attr("artistId"),$(this).attr("currentPage"));
    }
  },'.unfollowArtist');
});
function followArtist(artistId,currentPage) {
  $.ajax({
    url: "artist/followArtist",
    type: "POST",
    data: ({
      artistId: artistId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){});
    },
    error: function(){
      console.log("Failure following artist");
    }
  });
  return false;
};

function unfollowArtist(artistId,currentPage) {
  $.ajax({
    url: "artist/unfollowArtist",
    type: "POST",
    data: ({
      artistId: artistId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){});
    },
    error: function(){
      console.log("Failure unfollowing artist");
    }
  });
  return false;
};

function viewArtist(id){
  $.ajax({
    url: "artist/viewArtist",
    type: "GET",
    data: ({
      artistID: id
    }),
    success:function(){
      console.log("View success");
      $("#center-pane").load("/resources/pages/artist.jsp",function(){
        console.log("Loaded new artist info into center pane!");
      });
    },
    error: function(){
      console.log("View error");
    }
  });
  return false; // Makes sure that the link isn't followed
}
function viewFollowedArtists(){
  $("#center-pane").load("/resources/pages/followedArtists.jsp");
}