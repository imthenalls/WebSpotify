/** Used modules in 
 * https://msdn.microsoft.com/library/gg589528(v=vs.85).aspx?f=255&MSPPError=-2147217396#AddingAProgressBar*/

function progressBar() { 
    var oAudio = document.getElementById('audio'); 
    //get current time in seconds
    var elapsedTime = Math.round(oAudio.currentTime);
    //update the progress bar
    if (canvas.getContext) {
        var ctx = canvas.getContext("2d");
        //clear canvas before painting
        ctx.clearRect(0, 0, canvas.clientWidth, canvas.clientHeight);
        ctx.fillStyle = "#3399ff";
        var fWidth = (elapsedTime / oAudio.duration) * (canvas.clientWidth);
        if (fWidth > 0) {
            ctx.fillRect(0, 0, fWidth, canvas.clientHeight);
        }
    }
}

function initEvents() {
    var canvas = document.getElementById('canvas');  
    var oAudio = document.getElementById('audio');
    var icon = document.getElementById('playPauseIcon');
    
    //set up event to toggle play button to pause when playing
    playPause.addEventListener('click', function(){
        if (oAudio.paused || oAudio.ended) { //if the audio is paused or not playing,
          $(icon).removeClass("fa-play");
          $(icon).addClass("fa-pause");
          oAudio.play();
       }
       else {
          $(icon).removeClass("fa-pause");
          $(icon).addClass("fa-play");
          oAudio.pause();
       }
    }, false);
    
    //set up event to update the progress bar
    oAudio.addEventListener("timeupdate", progressBar, true); 
    //set up mouse click to control position of audio
    canvas.addEventListener("click", function(e) {
        //this might seem redundant, but this these are needed later - make global to remove these
        var oAudio = document.getElementById('audio'); 
        var canvas = document.getElementById('canvas');            

        if (!e) {
            e = window.event;
        } //get the latest windows event if it isn't set
        try {
            //calculate the current time based on position of mouse cursor in canvas box
            oAudio.currentTime = oAudio.duration * (e.offsetX / canvas.clientWidth);
            
            
        }
        catch (err) {
        // Fail silently but show in F12 developer tools console
            if (window.console && console.error("Error:" + err));
        }
    }, true);
}

window.addEventListener("DOMContentLoaded", initEvents, false);