var audio;

$(document).ready(function(){
    $("#center-pane").load("/resources/pages/browsePage.jsp");
    w3.includeHTML(playBack);
    $('#myCarousel').carousel({
	    interval: 10000
	})
    $(".next").click(function(){
        $("#myCarousel").carousel("prev");
    });
    $(".prev").click(function(){
        $("#myCarousel").carousel("next");
    });
    
    function playBack(){
        audio = $("#audio")[0];
        audio.addEventListener("loadedmetadata",function(){
            var duration = audio.duration;
            $('#songDuration')[0].innerHTML= Math.floor(duration/60) + ":" + Math.floor(duration % 60);
        });
        audio.addEventListener("timeupdate",updateProgress,false);
        //audio.controls=false;
    } 
    //var activeToggle = $("#browseToggle"); //By default, the center pane shown is the browse overview
    
    $("#newPlaylistForm").submit(function(){
        var playlistName = $("#pName").val();
        var imagePath = $("#iPath").val();
        var description = $("#pDesc").val();
        $.ajax({
            url: "makePlaylist",
            type: "POST",
            //Sends the necessary form parameters to the servlet
            data:({
               playlistName: playlistName,
               imagePath: imagePath,
               description: description
            }),
            success: function(){
                console.log("Success creating playlist");
                $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                    console.log("Reloaded playlist sidebar after add");
                });
            },
            error: function(){
                console.log("Failure creating playlist");
            }
        });
        $("#createPlaylistModal").modal('hide');
        return false;    
    });
    
});

function updateProgress() {
    var progress = $(".progress-bar")[0];
    var value = 0;
    if (audio.currentTime > 0)
        value = Math.floor((100 / audio.duration) * audio.currentTime);
    progress.style.width = value + "%";
    var currTime = $("#currentTime")[0];
    var t = audio.currentTime;  
    var less10="";
    var seconds = Math.floor(t%60);
    if (seconds < 10)
        less10 = "0";
    currTime.innerHTML= Math.floor(t/60) + ":" + less10 + seconds;
 }

function togglePlayPause() {
   var icon = $("#playPauseIcon")[0];
   if (audio.paused || audio.ended) { //if the audio is paused or not playing,
      $(icon).removeClass("fa-play");
      $(icon).addClass("fa-pause");
      audio.play();
   }
   else {
      $(icon).removeClass("fa-pause");
      $(icon).addClass("fa-play");
      audio.pause();
   }
}

function viewPlaylist(link){
    console.log("Viewing?");
    var id = link.substring(1,);
    $.ajax({
        url: "viewPlaylist",
        type: "GET",
        data: ({
            playlistID: id
        }),
        success:function(){
            console.log("View success");
            $("#center-pane").load("/resources/pages/playlist.jsp",function(){
                console.log("Loaded new playlist info into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewAlbum(link){
    console.log("Trying to view album");
    var id = link.substring(5,);
    console.log("albumid: ",id);
    $.ajax({
        url: "viewAlbum",
        type: "GET",
        data: ({
            albumID: id
        }),
        success:function(){
            console.log("View success");
            $("#center-pane").load("/resources/pages/album.jsp",function(){
                console.log("Loaded new album into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}
function viewFollowedAlbums(link){
    console.log("Trying to view followed albums");
    var id = link.substring(5,);
    $.ajax({
        url: "viewFollowedAlbums",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("/resources/pages/followedAlbums.jsp",function(){
                console.log("Loaded new album into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}
function viewSong(link){
    console.log("Viewing?");
    var id = link.substring(1,);
    $.ajax({
        url: "viewSongs",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("/resources/pages/song.jsp",function(){
                console.log("Loaded new playlist info into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
};

function deletePlaylist(){
    console.log("Deleting..");
    $.ajax({
        url: "deletePlaylist",
        type: "POST",
        data: ({}),
        success: function(){
            console.log("Success deleting playlist");
            $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
                $("#center-pane").load("/resources/pages/browsePage.jsp");
            });
        },
        error: function(){
            console.log("Failure deleting playlist");
        }
    })
    return false;
};
function addToPlaylist(playlist, song) {
  $.ajax({
    url: "addToPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
      song: song
    }),
    success:function(){
      console.log("Success adding song");
    },
    error: function(){
            console.log("Failure adding song");
    }
  });
  return false;
};
function deletePlaylist(){
    console.log("Deleting..");
    $.ajax({
        url: "deletePlaylist",
        type: "POST",
        data: ({}),
        success: function(){
            console.log("Success deleting playlist");
            $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
                $("#center-pane").load("/resources/pages/browsePage.jsp");
            });
        },
        error: function(){
            console.log("Failure deleting playlist");
        }
    })
    return false;
};
