$(document).ready(function(){
  //Unfollow Song
    $(document).on({
      click: function(){
        unfollowSong($(this).attr("songId"),$(this).attr("currentPage"));
      }
    },'.unfollowSong');
    
    $(document).on({
     click: function(){
       followSong($(this).attr("songId"),$(this).attr("currentPage"));
     }  
    },'.followSong');
    
    $(document).on({
      click: function(){
        $.ajax({
        url: "song/top",
        type: "GET",
        success:function(){
          $("#center-pane").load("/resources/pages/topSongs.jsp",function(){
                    console.log("Success unfollowing song");
                });
        },
        error: function(){
                console.log("Failure unfollowing song");
        }
      });
      return false;
          }
    },'#topWorld');
    
    $(document).on({
      click: function(){
        $.ajax({
        url: "song/seeMore",
        type: "GET",

        success:function(){
          $("#center-pane").load("/resources/pages/searchSongs.jsp",function(){
                    console.log("Success unfollowing song");
                });
        },
        error: function(){
                console.log("Failure unfollowing song");
        }
      });
      return false;
      }
    },'#moreSongs');
    
});

function followSong(songId,currentPage) {
  $.ajax({
    url: "song/followSong",
    type: "POST",
    data: ({
      songId: songId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
        console.log("Success following song");
      });
    },
    error: function(){
      console.log("Failure following song");
    }
  });
  return false;
};


function unfollowSong(songId,currentPage) {
  $.ajax({
    url: "song/unfollowSong",
    type: "POST",
    data: ({
      songId: songId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
                console.log("Success unfollowing song");
            });
    },
    error: function(){
            console.log("Failure unfollowing song");
    }
  });
  return false;
};

function viewFollowedSongs(){
  $("#center-pane").load("/resources/pages/followedSongs.jsp");
}

function viewTopSongs(){
  $("#center-pane").load("/resources/pages/charts/topSongs.jsp");
}
