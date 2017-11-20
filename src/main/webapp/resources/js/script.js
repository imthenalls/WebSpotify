var audio;
var progress = document.getElementById("progress");
var songbar = document.getElementById("songBar");

$(document).ready(function(){
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
        progress = document.getElementById("progress");
        songbar = document.getElementById("songBar");
        audio = document.getElementById("audio");
        audio.addEventListener("loadedmetadata",function(){
            var duration = audio.duration;
            $('#songDuration')[0].innerHTML= Math.floor(duration/60) + ":" + Math.floor(duration % 60);    
        });  
        //for firefox
        var duration = audio.duration;
        $('#songDuration')[0].innerHTML= Math.floor(duration/60) + ":" + Math.floor(duration % 60);
        audio.addEventListener("timeupdate",updateProgress,false);
        progress.addEventListener('click', scrub, false);
    } 
    
    function scrub(event){
        if(!audio.ended){
            var mousex  = event.pageX - (progress.offsetLeft*3);
            var newtime = mousex * (audio.duration/$(progress).width());
            audio.currentTime = newtime;
            //audio.setAttribute('currentTime', newtime);
            songbar.style.width = parseInt(newtime/audio.duration) + "%";
        }
    }
    var activeToggle = $("#browseToggle"); //By default, the center pane shown is the browse overview
    $(".click").click(function (){
        var link = $(this); // The link that was clicked
        var elem = $(link.attr("href")); // The component that will be potentially shown 
        if(elem[0].style.display === "none"){ // If the element is currently hidden    
            activeToggle[0].style.display = "none"; // Hide the currently shown content
            elem[0].style.display = "block"; // Displays the new content
            activeToggle = elem; // Updates the active pane variable
        }
        return false; // Makes sure that the link isn't followed
    });
    
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
                console.log("Success");
            },
            error: function(){
                console.log("Failure");
            }
        });
        $("#createPlaylistModal").modal('hide');
        return false;    
    });
    
});

$("#printInfo").click(function(){
    $.ajax({
        url: "printInfo",
        type: "GET",
        data:({}),
        success: function(){
            console.log("Success");
        },
        error: function(){
            console.log("Failure");
        }
    });
    return false;
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





